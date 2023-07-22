package com.example.mycart.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycart.Model.Address;
import com.example.mycart.R;

import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder>
{


    Context applicationContext;
    List<Address> mAddressList;

    SelectedAddress selectedAddress;
    private RadioButton mSelectedRadioButton;

    public AddressAdapter(Context applicationContext, List<Address> mAddressList, SelectedAddress selectedAddress)
    {
        this.applicationContext=applicationContext;
        this.mAddressList=mAddressList;
        this.selectedAddress=selectedAddress;
    }


    @NonNull
    @Override
    public AddressAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)

    {
        View view= LayoutInflater.from(applicationContext).inflate(R.layout.single_address_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position)
    {
         holder.mAddress.setText(mAddressList.get(position).getAddress());
         holder.mRadioButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v)
             {
                 for (Address address:mAddressList
                      )
                 {
                     address.setSelected(false);
                 }
                 mAddressList.get(position).setSelected(true);
                 if (mSelectedRadioButton!=null)
                 {
                    mSelectedRadioButton.setChecked(false);
                 }
                 mSelectedRadioButton=(RadioButton) v;
                 mSelectedRadioButton.setChecked(true);
                 selectedAddress.setAddress(mAddressList.get(position).getAddress());

             }
         });

    }



    @Override
    public int getItemCount()
    {
        return mAddressList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView mAddress;
        private RadioButton mRadioButton;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            mAddress=itemView.findViewById(R.id.address_add);
            mRadioButton=itemView.findViewById(R.id.select_address);
        }
    }
    public interface SelectedAddress
    {
       public void setAddress(String s);
    }
}
