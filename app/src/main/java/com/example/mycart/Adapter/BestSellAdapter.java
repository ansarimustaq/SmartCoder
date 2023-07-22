package com.example.mycart.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mycart.DetailActivity;
import com.example.mycart.Model.BestSell;
import com.example.mycart.R;

import java.util.List;

public class BestSellAdapter extends RecyclerView.Adapter<BestSellAdapter.ViewHolder>
{
    private Context context;
    private List<BestSell> mBestSellList;
    public BestSellAdapter(Context context, List<BestSell> mBestSellList)
    {
        this.context=context;
        this.mBestSellList=mBestSellList;

    }
    @SuppressLint("NotifyDataSetChanged")
    public void add(BestSell bestSell)
    {
        mBestSellList.add(bestSell);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.bestsell,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull BestSellAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position)
    {
        holder.mName.setText(mBestSellList.get(position).getName());
        holder.mPrice.setText(mBestSellList.get(position).getPrice()+" Price");
        Glide.with(context).load(mBestSellList.get(position).getImg_url()).into(holder.mImage);

         holder.mImage.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent=new Intent(context, DetailActivity.class);
                 intent.putExtra("detail",mBestSellList.get(position));
                 context.startActivity(intent);
             }
         });

    }



    @Override
    public int getItemCount()
    {
        return mBestSellList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView mImage;
        TextView mName;
        TextView mPrice;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            mImage=itemView.findViewById(R.id.bs_img);
            mName=itemView.findViewById(R.id.bs_name);
            mPrice=itemView.findViewById(R.id.bs_cost);
        }
    }
}
