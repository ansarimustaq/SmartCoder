package com.example.mycart.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mycart.Adapter.MenAdapter;
import com.example.mycart.R;

public class MensFragment extends Fragment
{
    RecyclerView recyclerView;

    Integer images[]={R.drawable.rr,R.drawable.pr,R.drawable.ep,R.drawable.eq,R.drawable.er,R.drawable.et,R.drawable.ew,R.drawable.rrp};
    String name[]={"Pink Shirt","navy Blue Shirt","light Blue Jeans","Light Fit Trouser","Printed Shirt","Black Pant","check Shirt","Check Style Shirt"};
    int price[]={860,1050,730,1599,1210,1520,1320,1230};



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_mens, container, false);
        recyclerView=view.findViewById(R.id.recyclerview);

        MenAdapter adapter=new MenAdapter(images,price,name, getActivity());
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerView.setAdapter(adapter);
        return view;
    }
}