package com.example.mycart.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycart.R;

public class KidsAdapter extends RecyclerView.Adapter<KidsAdapter.MyViewHolder>
{
    Integer images[];
    int price[];
    String name[];

    public KidsAdapter(Integer[] images, int[] price, String[] name, FragmentActivity activity)
    {
        this.images=images;
        this.price=price;
        this.name=name;
    }

    @NonNull
    @Override
    public KidsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_kids,parent, false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull KidsAdapter.MyViewHolder holder, int position)
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
        TextView name ,price;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageviewkids);
            name=itemView.findViewById(R.id.name1);
            price=itemView.findViewById(R.id.price1);
        }
    }
}
