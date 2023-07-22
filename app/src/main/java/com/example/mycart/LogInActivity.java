package com.example.mycart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogInActivity extends AppCompatActivity
{
    private EditText mEmail;
    private EditText mPassword;
    private Button mLoginBtn;
    private FirebaseAuth mAuth;
    private Toolbar mToolbar;
    private TextView signUp;
    //private TextView don_t_have_account;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        mEmail=findViewById(R.id.emailaddress);
        mPassword=findViewById(R.id.password);
        mLoginBtn=findViewById(R.id.signupButton10);
        mAuth=FirebaseAuth.getInstance();
        mToolbar=findViewById(R.id.toolbarr);
        signUp=findViewById(R.id.signup);
       // don_t_have_account=findViewById(R.id.textView5);
        //signUp=findViewById(R.id.textView5);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
               Intent intent=new Intent(LogInActivity.this,RegisterActivity.class);
               startActivity(intent);
            }
        });
        /*don_t_have_account.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
               Intent intent=new Intent(LogInActivity.this,RegisterActivity.class);
               startActivity(intent);
            }
        });*/


        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
               String email=mEmail.getText().toString();
               String password=mPassword.getText().toString();
               if(!email.isEmpty() && !password.isEmpty())
               {
                 mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task)
                     {
                         if (task.isSuccessful())
                         {
                             Toast.makeText(LogInActivity.this, "LogIn Successful!", Toast.LENGTH_SHORT).show();
                             Intent intent=new Intent(LogInActivity.this,MainActivity.class);
                             startActivity(intent);
                         }
                         else {
                             Toast.makeText(LogInActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                         }
                     }
                 }).addOnFailureListener(new OnFailureListener() {
                     @Override
                     public void onFailure(@NonNull Exception e) {
                         Toast.makeText(LogInActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                     }
                 });
               }else {
                   Toast.makeText(LogInActivity.this, "Please fill empty Field", Toast.LENGTH_SHORT).show();
               }
            }
        });
    }
    public void signUp(View view)
    {
       Intent intent=new Intent(LogInActivity.this,RegisterActivity.class);
       startActivity(intent);
    }
}