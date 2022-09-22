package com.example.dennispersaudinventoryapplication.feature_inventory.domain.repository;

import androidx.lifecycle.LiveData;


import com.example.dennispersaudinventoryapplication.feature_inventory.domain.model.Item;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface ItemRepository {
    LiveData<List<Item>> loadAllItems();
     void insertItem(Item item);
     void updateItem(Item item);
     void deleteItem(Item item);
    Item getItemByName(String name) throws ExecutionException, InterruptedException;
}
