package com.example.mycart;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycart.Adapter.ItemsRecyclerAdapter;
import com.example.mycart.Model.Items;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity
{
    Fragment homeFragment;
    private FirebaseAuth mAuth;
    private FirebaseFirestore mStore;
    private List<Items> mItemsList;
    private RecyclerView mItemRecyclerView;
    private ItemsRecyclerAdapter itemsRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        homeFragment=new HomeFragment();
        loadFragment(homeFragment);
        mAuth=FirebaseAuth.getInstance();
        Toolbar mToolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(mToolbar);
        EditText mSearchtext = findViewById(R.id.search_text);
        mStore=FirebaseFirestore.getInstance();
        mItemsList=new ArrayList<>();
        mItemRecyclerView=findViewById(R.id.search_recycler);
        mItemRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        itemsRecyclerAdapter=new ItemsRecyclerAdapter(this,mItemsList);
        mItemRecyclerView.setAdapter(itemsRecyclerAdapter);
        mSearchtext.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                 searchItem(editable.toString());
            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void searchItem(String text)
    {
        if (!text.isEmpty())
        {
            mStore.collection("All").whereGreaterThanOrEqualTo("name",text).get()
                    .addOnCompleteListener(task -> {
                        if(task.isSuccessful() && task.getResult()!=null){
                            mItemsList.clear();
                            for(DocumentSnapshot doc:task.getResult().getDocuments()){
                                Items items=doc.toObject(Items.class);
                                if(!mItemsList.contains(items)){
                                    mItemsList.add(items);

                                }
                            }
                            itemsRecyclerAdapter=new ItemsRecyclerAdapter(getApplicationContext(),mItemsList);
                            mItemRecyclerView.setAdapter(itemsRecyclerAdapter);
                            itemsRecyclerAdapter.notifyDataSetChanged();

                        }
                    });
        }
        else
        {
            mItemsList.clear();
            itemsRecyclerAdapter=new ItemsRecyclerAdapter(getApplicationContext(),new ArrayList<>());
            mItemRecyclerView.setAdapter(itemsRecyclerAdapter);
            itemsRecyclerAdapter.notifyDataSetChanged();
        }




    }
    private void loadFragment(Fragment homeFragment)
    {
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.home_container,homeFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if (item.getItemId()==R.id.logout)
        {
            mAuth.signOut();
            Intent intent=new Intent(HomeActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        if (item.getItemId()==R.id.cart)
        {
            Intent intent=new Intent(HomeActivity.this,CartFragment.class);
            startActivity(intent);
        }
        return true;
    }
}