package com.example.mycart.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycart.R;

public class MenAdapter extends RecyclerView.Adapter<MenAdapter.MyViewHolder>
{
    Integer images[];
    int price[];
    String name[];


    public MenAdapter(Integer[] images, int[] price, String[] name, FragmentActivity activity)
    {
        this.images=images;
        this.price=price;
        this.name=name;
    }

    @NonNull
    @Override
    public MenAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {    View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_men,parent, false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MenAdapter.MyViewHolder holder, int position)
    {
        holder.imageView.setImageResource(images[position]);
        holder.name.setText(" "+name[position]);
        holder.price.setText("Rs "+price[position]);
    }

    @Override
    public int getItemCount()
    {
        return images.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        TextView name,price;

        public MyViewHolder(@NonNull View itemView)

        {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageviewmens);
            name=itemView.findViewById(R.id.name);
            price=itemView.findViewById(R.id.price);

        }
    }
}
