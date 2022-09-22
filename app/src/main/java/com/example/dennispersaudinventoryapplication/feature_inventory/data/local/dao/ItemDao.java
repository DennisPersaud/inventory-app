package com.example.dennispersaudinventoryapplication.feature_inventory.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dennispersaudinventoryapplication.feature_inventory.domain.model.Item;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Dao
public interface ItemDao {

    @Insert
    void insertItem(Item item);

    @Update
    void updateItem(Item item);

    @Delete
    void deleteItem(Item item);

    // Return list of all item objects in the database
    @Query("SELECT * FROM item_table")
    List<Item> getAllItems() throws ExecutionException, InterruptedException;

    // Return user specified by name
    @Query("SELECT * FROM item_table WHERE item_name = :name")
    Item getItemByName(String name) throws ExecutionException, InterruptedException;

    @Query("SELECT * FROM item_table")
    LiveData<List<Item>> loadAllItems();
}