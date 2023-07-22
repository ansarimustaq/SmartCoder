package com.example.mycart;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycart.Adapter.AddressAdapter;
import com.example.mycart.Model.Address;
import com.example.mycart.Model.BestSell;
import com.example.mycart.Model.Feature;
import com.example.mycart.Model.Items;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddressActivity extends AppCompatActivity implements AddressAdapter.SelectedAddress,setAddress
{
    private AddressAdapter mAddressAdapter;
    List<Address> mAddressList;
    String address="";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        final Object obj=getIntent().getSerializableExtra("item");
        List<Items> itemsList=(ArrayList<Items>) getIntent().getSerializableExtra("itemList");
        RecyclerView mAddressRecyclerView = findViewById(R.id.address_recycler);
        Button paymentBtn = findViewById(R.id.payment_btn);
        Button mAddAddress = findViewById(R.id.add_address_btn);
        Toolbar mToolbar = findViewById(R.id.address_toolbar);
        setSupportActionBar(mToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseFirestore mStore = FirebaseFirestore.getInstance();
        mAddressList=new ArrayList<>();
        mAddressAdapter=new AddressAdapter(getApplicationContext(),mAddressList,this);
        mAddressRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mAddressRecyclerView.setAdapter(mAddressAdapter);

        mStore.collection("User").document(Objects.requireNonNull(mAuth.getCurrentUser()).getUid())
                .collection("Address").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task)
                    {
                       if (task.isSuccessful())
                       {
                          for (DocumentSnapshot doc:task.getResult().getDocuments())
                          {
                            Address address1=doc.toObject(Address.class);
                            mAddressList.add(address1);
                            mAddressAdapter.notifyDataSetChanged();
                          }
                       }
                    }
                });
                mAddAddress.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                          Intent intent=new Intent(AddressActivity.this,AddAddressActivity.class);
                          startActivity(intent);
                    }
                });
                paymentBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view)
                    {
                         double amount=0.0;
                         String url="";
                         String name="";

                         if (obj instanceof Feature)
                         {
                                 Feature f=(Feature) obj;
                                 amount=f.getPrice();
                                 url=f.getImg_url();
                                 name=f.getName();

                         }
                         if (obj instanceof BestSell)
                         {
                             BestSell b=(BestSell) obj;
                             amount=b.getPrice();
                             url=b.getImg_url();
                             name=b.getName();
                         }
                         if (obj instanceof Items)
                         {
                             Items i=(Items) obj;
                             amount=i.getPrice();
                             url=i.getImg_url();
                             name=i.getName();
                         }
                         if (itemsList!=null  && itemsList.size()>0)
                         {
                             Intent intent=new Intent(AddressActivity.this,PaymentActivity.class);
                              intent.putExtra("itemList",(Serializable) itemsList );
                              startActivity(intent);
                         }
                         else {
                                  Intent intent=new Intent(AddressActivity.this,PaymentActivity.class);
                                  intent.putExtra("amount",amount);
                                  intent.putExtra("img_url",url);
                                  intent.putExtra("name",name);
                                  intent.putExtra("address",address);
                                  startActivity(intent);

                         }
                    }
                });
    }
    @Override
    public void setAddress(String s)
    {address=s;}

}