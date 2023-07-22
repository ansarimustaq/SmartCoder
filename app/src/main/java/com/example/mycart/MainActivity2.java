package com.example.mycart;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity2 extends AppCompatActivity
{
    ImageView imageView;
    TextView textView;
    Button add_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        imageView=findViewById(R.id.imageviewitem);
        textView=findViewById(R.id.details);
        add_item=findViewById(R.id.aa_item);

    }
}