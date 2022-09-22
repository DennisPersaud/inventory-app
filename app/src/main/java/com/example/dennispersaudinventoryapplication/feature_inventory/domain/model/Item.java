package com.example.dennispersaudinventoryapplication.feature_inventory.domain.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.dennispersaudinventoryapplication.feature_inventory.domain.model.value_objects.ItemCount;
import com.example.dennispersaudinventoryapplication.feature_inventory.domain.model.value_objects.ItemName;
import com.example.dennispersaudinventoryapplication.feature_inventory.domain.model.value_objects.ItemPrice;

@Entity(tableName = "item_table")
public class Item {

    @ColumnInfo(name = "item_id")
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "item_name")
    private ItemName itemName;

    @ColumnInfo(name = "item_price")
    private ItemPrice itemPrice;

    @ColumnInfo(name = "item_count")
    private ItemCount itemCount;

    // Constructors
    public Item(ItemName itemName, ItemPrice itemPrice, ItemCount itemCount) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCount = itemCount;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ItemName getItemName() {
        return itemName;
    }

    public void setItemName(ItemName itemName) {
        this.itemName = itemName;
    }

    public ItemPrice getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(ItemPrice itemPrice) {
        this.itemPrice = itemPrice;
    }

    public ItemCount getItemCount() {
        return itemCount;
    }

    public void setItemCount(ItemCount itemCount) {
        this.itemCount = itemCount;
    }

    public Item getInstance() { return this; }
}
