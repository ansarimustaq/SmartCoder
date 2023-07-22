package com.example.mycart.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycart.Model.ItemDetail;
import com.example.mycart.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ItemDetailAdapter extends RecyclerView.Adapter<ItemDetailAdapter.MyViewHolder>
{
    ArrayList<ItemDetail> list;
    Context context;


    public ItemDetailAdapter(ArrayList<ItemDetail> list, Context context)
    {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public ItemDetailAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View myView = LayoutInflater.from(context).inflate(R.layout.item_detail,parent, false);
        MyViewHolder holder=new MyViewHolder(myView);
        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ItemDetailAdapter.MyViewHolder holder, int position)
    {
         ItemDetail detail=list.get(position);
         holder.textView.setText("Name:-"+detail.getName());
         holder.textView1.setText("Price:-"+detail.getPrice());
         Picasso.get().load(detail.getUrl()).into(holder.imageView);

    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        TextView textView1;
        TextView textView;
        public MyViewHolder(@NonNull View itemView)
        {

            super(itemView);
            imageView=itemView.findViewById(R.id.imgview);
            textView=itemView.findViewById(R.id.name11);
            textView1=itemView.findViewById(R.id.pric);
        }
    }
}
