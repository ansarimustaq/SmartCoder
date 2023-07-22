package com.example.mycart;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.mycart.Adapter.BestSellAdapter;
import com.example.mycart.Adapter.CategoryAdapter;
import com.example.mycart.Adapter.FeatureAdapter;
import com.example.mycart.Model.BestSell;
import com.example.mycart.Model.Category;
import com.example.mycart.Model.Feature;
import com.example.mycart.Model.ItemDetail;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    ImageSlider imageSlider;
    //Category Tab
    private List<Category> mCategoryList;
    private CategoryAdapter mCategoryAdapter;
    //Feature Tab
    private List<Feature> mFeatureList;
    private FeatureAdapter mFeatureAdapter;
    RecyclerView mCatRecyclerView;
    RecyclerView mFeatureRecyclerView;
    RecyclerView mBestSellRecyclerView;
    //BestSell Tab
    private List<BestSell> mBestSellList;
    private BestSellAdapter mBestSellAdapter;
    TextView mSeeAll;
    TextView mFeature;
    TextView mBestSell;
    ArrayList<ItemDetail> list;
    FirebaseDatabase database;
    FirebaseFirestore mStore;
    DatabaseReference reference;

    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;

    public HomeFragment()
    {

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        toolbar = view.findViewById(R.id.toolbar);
        navigationView = view.findViewById(R.id.navigation);
        drawerLayout = view.findViewById(R.id.drawerlayout);
        toggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.open_nav,R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        database = FirebaseDatabase.getInstance();
        mStore = FirebaseFirestore.getInstance();
        mCatRecyclerView = view.findViewById(R.id.category_recycler);
        mBestSellRecyclerView = view.findViewById(R.id.bestsell_recycler);
        mFeatureRecyclerView = view.findViewById(R.id.feature_recycler);
        reference = database.getReference("data");

        list = new ArrayList<ItemDetail>();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                switch (item.getItemId())
                {
                    case R.id.setting:
                        Toast.makeText(getActivity(), "this is setting", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.contact_us:
                        Toast.makeText(getActivity(), "this is contact us", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.about_us:
                        Toast.makeText(getActivity(), "this is about us", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.logout:
                        Toast.makeText(getActivity(), "this is logout", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });



       /* reference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                for
                (DataSnapshot datasnapshot:snapshot.getChildren()
                )
                {
                    ItemDetail object=datasnapshot.getValue(ItemDetail.class);
                    list.add(object);
                }
                ItemDetailAdapter adapter=new ItemDetailAdapter(list,getContext());
                mCatRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2,LinearLayoutManager.HORIZONTAL,false));
                mCatRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });
        reference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                for (DataSnapshot datasnapshot:snapshot.getChildren()
                )
                {
                    ItemDetail object=datasnapshot.getValue(ItemDetail.class);
                    list.add(object);
                }
                ItemDetailAdapter adapter=new ItemDetailAdapter(list,getContext());
                mFeatureRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2,LinearLayoutManager.HORIZONTAL,false));
                mFeatureRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });
        reference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                for (DataSnapshot datasnapshot:snapshot.getChildren()
                )
                {
                    ItemDetail object=datasnapshot.getValue(ItemDetail.class);
                    list.add(object);
                }
                ItemDetailAdapter adapter=new ItemDetailAdapter(list,getContext());
                mBestSellRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2,LinearLayoutManager.HORIZONTAL,false));
                mBestSellRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });*/

        imageSlider = view.findViewById(R.id.image_slider);
        ArrayList<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.ac, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.soo, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.pic, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.sport, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);
        mStore = FirebaseFirestore.getInstance();
        mSeeAll = view.findViewById(R.id.see_all);
        mFeature = view.findViewById(R.id.fea_see_all);
        mBestSell = view.findViewById(R.id.best_sell);
        mCatRecyclerView = view.findViewById(R.id.category_recycler);
        mFeatureRecyclerView = view.findViewById(R.id.feature_recycler);
        mBestSellRecyclerView = view.findViewById(R.id.bestsell_recycler);
        //For Category
        mCategoryList = new ArrayList<>();
        mCategoryAdapter = new CategoryAdapter(getContext(), mCategoryList);
        mCatRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        mCatRecyclerView.setAdapter(mCategoryAdapter);

        //For Feature
        mFeatureList = new ArrayList<>();
        mFeatureAdapter = new FeatureAdapter(getContext(), mFeatureList);
        mFeatureRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        mFeatureRecyclerView.setAdapter(mFeatureAdapter);
        //For BestSell
        mBestSellList = new ArrayList<>();
        mBestSellAdapter = new BestSellAdapter(getContext(), mBestSellList);
        mBestSellRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        mBestSellRecyclerView.setAdapter(mBestSellAdapter);


        mStore.collection("Category")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Category category = document.toObject(Category.class);
                                mCategoryList.add(category);
                                mCategoryAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }
                    }
                });
        mStore.collection("Feature")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Feature feature = document.toObject(Feature.class);
                                mFeatureList.add(feature);
                                mFeatureAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }
                    }
                });
        mStore.collection("BestSell")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                BestSell bestSell = document.toObject(BestSell.class);
                                mBestSellList.add(bestSell);
                                mBestSellAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }
                    }
                });
        mSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getFragmentManager().beginTransaction().replace(R.id.main_page, new CategoryFragment()).commit();
                Intent intent = new Intent(getActivity(), ItemsActivity.class);
                startActivity(intent);
            }
        });
        mBestSell.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ItemsActivity.class);
                startActivity(intent);
            }
        });
        mFeature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getContext(), ItemsActivity.class);
                //getFragmentManager().beginTransaction().replace(R.id.main_page,new WomenFragment()).commit();
                //Intent intent=new Intent(getContext(), WomenFragment.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
