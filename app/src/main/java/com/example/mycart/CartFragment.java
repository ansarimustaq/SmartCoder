package com.example.mycart;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycart.Adapter.CartItemAdapter;
import com.example.mycart.Model.Items;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment implements CartItemAdapter.ItemRemoved
{
   // RecyclerView recyclerView;
    //ArrayList<ItemDetail> list;
   // FirebaseDatabase database;
    //DatabaseReference reference;
   FirebaseFirestore mStore;
    FirebaseAuth mAuth;
    List<Items> itemsList;
    RecyclerView cartRecyclerView;
    CartItemAdapter cartItemAdapter;
    Button buyItemButton;
    TextView totalAmount;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_cart, container, false);
        mStore=FirebaseFirestore.getInstance();
        mAuth=FirebaseAuth.getInstance();
        itemsList=new ArrayList<>();
        cartRecyclerView=view.findViewById(R.id.cart_item_container);
        buyItemButton=view.findViewById(R.id.cart_item_buy_now);
        totalAmount=view.findViewById(R.id.total_amount);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        cartRecyclerView.setHasFixedSize(true);

buyItemButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view)
    {
        Intent intent=new Intent(getActivity(), AddressActivity.class);
        intent.putExtra("itemList",(Serializable) itemsList);
        startActivity(intent);
    }
});

         cartItemAdapter=new CartItemAdapter (itemsList,this);
         cartRecyclerView.setAdapter(cartItemAdapter);
         mStore.collection("Users").document(mAuth.getCurrentUser().getUid())
                 .collection("Cart").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                     @Override
                     public void onComplete(@NonNull Task<QuerySnapshot> task)
                     {
                          if (task.isSuccessful())
                          {
                              for (DocumentChange doc :task.getResult().getDocumentChanges())
                              {
                                  String documentId=doc.getDocument().getId();
                                  Items item=doc.getDocument().toObject(Items.class);
                                  item.setDocId(documentId);
                                   itemsList.add(item);
                              }
                                calculateAmount(itemsList);
                              cartItemAdapter.notifyDataSetChanged();
                          }else
                          {
                              Toast.makeText(getActivity(),task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                          }
                     }
                 });


        //recyclerView=view.findViewById(R.id.recyclerview100);
       // database=FirebaseDatabase.getInstance();
        //reference=database.getReference("data");
        //list=new ArrayList<ItemDetail>();
        //reference.addValueEventListener(new ValueEventListener()
        /*{
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                for (DataSnapshot datasnapshot:snapshot.getChildren()
                     )
                {
                  ItemDetail object=datasnapshot.getValue(ItemDetail.class);
                  list.add(object);
                }
                ItemDetailAdapter adapter=new ItemDetailAdapter(list,getActivity());
                recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2,LinearLayoutManager.VERTICAL,false));
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });*/
        return view;
    }

    private void calculateAmount(List<Items> itemsList)
    {
        double totalAmountInDouble=0.0;
        for (Items item:itemsList)
        {
            totalAmountInDouble+=item.getPrice();
        }
        totalAmount.setText("Total Amount: "+totalAmountInDouble);
    }


    @Override
    public void onItemRemoved(List<Items> itemsList)
    {
       calculateAmount(itemsList);
    }
}