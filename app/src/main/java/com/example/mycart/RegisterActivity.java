package com.example.mycart;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity
{
   // CountryCodePicker countryCodePicker;
    EditText name,email,password;
    Button regbtn;
    TextView Already_user;
    private FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    private ProgressBar progressbar;

    //String emailPattern="[a-zA-Z._-]+@[a-z]+\\.+[a-z]+";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        name = findViewById(R.id.phonenumberedittext);
        email = findViewById(R.id.emailaddressedittext);
        password = findViewById(R.id.passwordedittext);
        Already_user = findViewById(R.id.already);
        progressbar=findViewById(R.id.progress_bar);
        //signwithgoogle=findViewById(R.id.signwithgoogle);
        regbtn= findViewById(R.id.signupButton1);
        //login=findViewById(R.id.loginButton1);
        //countryCodePicker=findViewById(R.id.ccp);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        progressDialog = new ProgressDialog(this);

        Already_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(RegisterActivity.this, LogInActivity.class);
                startActivity(intent);
            }
        });


       /* signwithgoogle.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });*/
        regbtn.setOnClickListener(new View.OnClickListener() {
           // final String m_name = name.getText().toString();
            //final String m_email = email.getText().toString();
            //final String m_password = password.getText().toString();

            @Override
            public void onClick(View view)
            {
               signIn();

            }
        });
    }

    private void signIn()
    {
        String u_name=name.getText().toString();
        String u_email=email.getText().toString();
        String u_password=password.getText().toString();
        progressbar.setVisibility(View.VISIBLE);

        if (TextUtils.isEmpty(u_name))
        {
            Toast.makeText(this, "Please enter name", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(u_email))
        {
            Toast.makeText(this, "please enter email", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(u_password))
        {
            Toast.makeText(this, "please enter password", Toast.LENGTH_LONG).show();
            return;
        }

            mAuth.createUserWithEmailAndPassword(u_email, u_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, "Account Successfully Created", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                        progressbar.setVisibility(View.GONE);
                        //i.putExtra("mobile",countryCodePicker.getFullNumberWithPlus().replace("",""));
                        startActivity(i);
                    } else
                    {
                        progressbar.setVisibility(View.GONE);

                        Toast.makeText(RegisterActivity.this, "" + task.getException(), Toast.LENGTH_LONG).show();
                    }
                }
            });
         }
        }


        /****login.setOnClickListener(new View.OnClickListener()
        {
            String memail=email.getText().toString();
            String mpassword=password.getText().toString();
            @Override
            public void onClick(View view)
            {
                if (memail.isEmpty() && mpassword.isEmpty())
                {
                 mAuth.signInWithEmailAndPassword(memail,mpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task)
                     {
                         if (task.isSuccessful())
                         {
                             Toast.makeText(RegisterActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                             Intent i=new Intent(RegisterActivity.this,HomeActivity.class);
                             startActivity(i);
                         }else
                         {
                             Toast.makeText(RegisterActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                         }

                     }
                 }).addOnFailureListener(new OnFailureListener() {
                     @Override
                     public void onFailure(@NonNull Exception e)
                     {
                         Toast.makeText(RegisterActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                     }
                 });
                }else
                {
                    Toast.makeText(RegisterActivity.this, "Please fill empty field", Toast.LENGTH_SHORT).show();
                }


            }
        });

         */



