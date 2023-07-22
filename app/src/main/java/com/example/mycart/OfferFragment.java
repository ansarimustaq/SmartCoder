package com.example.mycart;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mycart.Adapter.OfferAdapter;


public class OfferFragment extends Fragment
{
    RecyclerView recyclerView;
    Integer images[]={};

    int price[]={};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_offer, container, false);
        recyclerView=view.findViewById(R.id.recyclerview00);


        OfferAdapter adapter=new OfferAdapter(images,price,getActivity());
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerView.setAdapter(adapter);

        return view;
    }


}