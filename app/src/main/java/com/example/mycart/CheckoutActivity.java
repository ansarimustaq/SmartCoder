package com.example.mycart;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.common.reflect.TypeToken;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stripe.android.ApiResultCallback;
import com.stripe.android.PaymentIntentResult;
import com.stripe.android.Stripe;
import com.stripe.android.model.ConfirmPaymentIntentParams;
import com.stripe.android.model.PaymentIntent;
import com.stripe.android.model.PaymentMethodCreateParams;
import com.stripe.android.model.StripeIntent;
import com.stripe.android.view.CardInputWidget;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CheckoutActivity extends AppCompatActivity
{
    private static final String BACKEND_URL = "https://stripe-payment-backend-android.herokuapp.com/";
    private OkHttpClient httpClient = new OkHttpClient();
    private String paymentIntentClientSecret;
    private Stripe stripe;
    Double amountDouble=null;
    String name="";
    String img_url = "";
    private FirebaseAuth mAuth;
    FirebaseFirestore mStore;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        mStore=FirebaseFirestore.getInstance();
        mAuth=FirebaseAuth.getInstance();
        amountDouble=getIntent().getDoubleExtra("amount",0.0);
        name=getIntent().getStringExtra("name");
        img_url=getIntent().getStringExtra("img_url");

        stripe=new Stripe(getApplicationContext(),Objects.requireNonNull("pk_test_QepzQuDrjs6aE8xnKrpFaVs200mW5WR4Pn"));
        startCheckout();

    }
    private void startCheckout()
    {
       MediaType mediaType=MediaType.get("application/json; charset=utf-8");
       double amount=amountDouble*100;
       Map<String,Object> payMap=new HashMap<>();
       Map<String,Object> itemMap=new HashMap<>();
       List<Map<String,Object>> itemList=new ArrayList<>();

       payMap.put("currency","INR");
       payMap.put("items",itemList);
       itemMap.put("id","photo_subscription");
       itemMap.put("amount",amount);
      itemList.add(itemMap);

      String json=new Gson().toJson(payMap);
      RequestBody body=RequestBody.create(mediaType, json);
      Request request=new Request.Builder().url(BACKEND_URL + "create-payment-intent" ).post(body).build();

      httpClient.newCall(request).enqueue(new PayCallback( this));

      Button payButton=findViewById(R.id.payButton);
      payButton.setOnClickListener((View view) ->{
         CardInputWidget cardInputWidget=findViewById(R.id.cardinputwidget);
         PaymentMethodCreateParams params=cardInputWidget.getPaymentMethodCreateParams();
         if (params!=null)
         {
            ConfirmPaymentIntentParams confirmParams=ConfirmPaymentIntentParams
                    .createWithPaymentMethodCreateParams(params,paymentIntentClientSecret);
            stripe.confirmPayment(this,confirmParams);
         }

    });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        stripe.onPaymentResult(requestCode,data, new PaymentResultCallback( this));
    }
private static  final class PayCallback implements Callback
{
    @NonNull private final WeakReference<CheckoutActivity> activityRef;

    private PayCallback(@NonNull CheckoutActivity activity)
    {
        activityRef = new WeakReference<>(activity);
    }

    @Override
    public void onFailure(Call call, IOException e)
    {
      final CheckoutActivity activity=activityRef.get();
      if (activity==null)
      {
          return;
      }
      activity.runOnUiThread(()-> Toast.makeText(activity, "Error "+e.toString(), Toast.LENGTH_SHORT).show());
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException
    {
         final CheckoutActivity activity = activityRef.get();
         if (activity==null)
         {
             return;
         }
         if (!response.isSuccessful())
         {
            activity.runOnUiThread(()-> Toast.makeText(activity, "Error" + response.toString(), Toast.LENGTH_SHORT).show());
         }else
         {
           activity.onPaymentSuccess(response);
         }
    }
}
    private void onPaymentSuccess(@NonNull final Response response) throws IOException
      {
           Gson gson=new Gson();
          Type type=new TypeToken<Map<String, String>>(){}.getType();
          Map<String, String> responseMap=gson.fromJson(Objects.requireNonNull(response.body()).string(),type);
          paymentIntentClientSecret=responseMap.get("clientSecret");
          Log.i("TAG","onPaymentSuccess: "+paymentIntentClientSecret);
      }
       private final class PaymentResultCallback implements
               ApiResultCallback<PaymentIntentResult>
       {
           @NonNull private final WeakReference<CheckoutActivity> activityRef;

           PaymentResultCallback(@NonNull CheckoutActivity activity)
           {
              activityRef=new WeakReference<>(activity);
           }

           @Override
           public void onError(@NonNull Exception e)
           {
               final CheckoutActivity activity=activityRef.get();
               if (activity==null)
               {
                  return;
               }
                  activity.displayAlert("Error", e.toString());

           }

           @Override
           public void onSuccess(PaymentIntentResult paymentIntentResult)
           {
               final CheckoutActivity activity=activityRef.get();
               if (activity!=null)
               {
                   return;
               }
               PaymentIntent paymentIntent=paymentIntentResult.getIntent();
               StripeIntent.Status status=paymentIntent.getStatus();
               if (status==StripeIntent.Status.Succeeded)
               {
                   Gson gson=new GsonBuilder().setPrettyPrinting().create();
                   Toast.makeText(activity, "Ordered Successful", Toast.LENGTH_SHORT).show();

                   Map<String, Object> mMap= new HashMap<>();
                   mMap.put("name",name);
                   mMap.put("img_url",img_url);
                   mMap.put("payment_id",paymentIntent.getPaymentMethodId());

                   mStore.collection("Users").document(mAuth.getCurrentUser().getUid())
                           .collection("Orders").add(mMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                               @Override
                               public void onComplete(@NonNull Task<DocumentReference> task)
                               {
                                   Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                                   startActivity(intent);
                                   finish();
                               }
                           });

               } else if (status==StripeIntent.Status.RequiresPaymentMethod)
               {
                activity.displayAlert("payment Failed",Objects.requireNonNull(paymentIntent.getLastPaymentError()).getMessage());
               }
           }
       }

    private void displayAlert(@NonNull String title,
                              @NonNull String message)
    {
         AlertDialog.Builder builder=new AlertDialog.Builder(this).setTitle(title).setMessage(message);
         builder.setPositiveButton("OK",null);
         builder.create().show();
    }
}