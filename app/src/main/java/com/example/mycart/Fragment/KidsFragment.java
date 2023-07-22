package com.example.mycart.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mycart.Adapter.KidsAdapter;
import com.example.mycart.R;

public class KidsFragment extends Fragment
{
    RecyclerView recyclerView;
    Integer images[]={R.drawable.tr,R.drawable.trp,R.drawable.or,R.drawable.cc,R.drawable.tp,R.drawable.ty,R.drawable.jean,R.drawable.igi};
    String name[]={"navy Blue Coat","Stylish Coat","Black and Green ","Style Coat","White Coat","Blue Jeans","denim jeans Shirt","Cow boy Set"};
    int price[]={640,540,560,670,590,780,1020,890};



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_kids, container, false);
        recyclerView=view.findViewById(R.id.recyclerview1);

        KidsAdapter adapter= new KidsAdapter(images,price,name,getActivity());
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerView.setAdapter(adapter);

        return view;
    }
}