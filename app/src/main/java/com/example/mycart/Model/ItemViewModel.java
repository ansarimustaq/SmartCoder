package com.example.mycart.Model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class ItemViewModel extends ViewModel
{
      private MutableLiveData<List<Item>> items;

      public MutableLiveData<List<Item>> getItems()
      {
         if (items == null) {
            items = new MutableLiveData<List<Item>>();
            loadItems();
         }
         return items;
      }

      private void loadItems()
      {
         // Load the items from a data source
         //List<Item> itemList = new ArrayList<>();
          List<Item> itemList = new ArrayList<>();
         itemList.add(new Item("image1.jpg", "Details 1"));
         itemList.add(new Item("image2.jpg", "Details 2"));
         itemList.add(new Item("image3.jpg", "Details 3"));
         items.setValue(itemList);
      }

}
