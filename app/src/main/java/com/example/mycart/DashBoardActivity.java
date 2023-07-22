package com.example.mycart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DashBoardActivity extends AppCompatActivity
{

//Toolbar toolbar;
    TextView home;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        //toolbar = findViewById(R.id.navigation);
       // setSupportActionBar(toolbar);

        home=findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent i =new Intent(DashBoardActivity.this,MainActivity.class);
                startActivity(i);
            }
        });

    }
}