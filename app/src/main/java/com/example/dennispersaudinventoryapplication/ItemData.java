package com.example.dennispersaudinventoryapplication;

public class ItemData {

    // Initialize variables
    private int SKU;
    private String itemName;
    private int itemPrice, itemCount;

    // Constructors
    public ItemData(int SKU, String itemName, int itemPrice, int itemCount) {
        this.SKU = SKU;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCount = itemCount;
    }

    // Return contents of itemData class object
    @Override
    public String toString() {
        return "ItemData{" +
                "SKU=" + SKU +
                ", itemName='" + itemName + '\'' +
                ", itemPrice=" + itemPrice +
                ", itemCount=" + itemCount +
                '}';
    }

    // Getters and Setters
    public int getSKU() {
        return SKU;
    }

    public void setSKU(int SKU) {
        this.SKU = SKU;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }
}
