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

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.MyViewHolder>
{
    Integer images[];
    int price[];
    

    public OfferAdapter(Integer[] images, int[] price, FragmentActivity activity)
    {
        this.images=images;
        this.price=price;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_offer,parent, false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {

    }
    @Override
    public int getItemCount()
    {
        return images.length;
    }
   public class MyViewHolder extends RecyclerView.ViewHolder
   {
       ImageView imageView;
       TextView price;

       public MyViewHolder(@NonNull View itemView)
       {
           super(itemView);
           imageView=itemView.findViewById(R.id.image_viewoffer);
           price=itemView.findViewById(R.id.offerprice);

       }
   }
}
