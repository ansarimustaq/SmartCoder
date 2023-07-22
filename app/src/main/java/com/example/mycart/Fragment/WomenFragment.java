package com.example.mycart.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mycart.Adapter.WomenAdapter;
import com.example.mycart.R;

public class WomenFragment extends Fragment
{
    RecyclerView recyclerView;
    Integer images[]={R.drawable.pz,R.drawable.bcc,R.drawable.cpp,R.drawable.tk,R.drawable.tky,R.drawable.ptt,R.drawable.pl,R.drawable.wome};
    String name[]={"Style ","navy Blue ","light Blue ","Light Fit Trouser","Printed ","Black ","check ","Check Style "};
    int price[]={860,1050,730,1599,1210,1520,1320,1230};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_women, container, false);
        recyclerView=view.findViewById(R.id.recyclerview2);
        WomenAdapter adapter=new WomenAdapter(images,price,name, getActivity());
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerView.setAdapter(adapter);

        return view;
    }
}