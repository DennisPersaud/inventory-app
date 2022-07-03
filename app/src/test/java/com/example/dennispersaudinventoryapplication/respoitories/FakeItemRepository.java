package com.example.dennispersaudinventoryapplication.respoitories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.dennispersaudinventoryapplication.db.model.Item;
import com.example.dennispersaudinventoryapplication.db.repo.ItemRepository;

import java.util.ArrayList;
import java.util.List;

public class FakeItemRepository implements ItemRepository {

    private final ArrayList<Item> itemList = new ArrayList<>();
    private final MutableLiveData<List<Item>> observableItem = new MutableLiveData<>(itemList);


    @Override
    public LiveData<List<Item>> loadAllItems() {
        return observableItem;
    }

    @Override
    public void insertItem(Item item) {
        itemList.add(item);
    }

    @Override
    public void updateItem(Item item) {
        itemList.set(0, item);
    }

    @Override
    public void deleteItem(Item item) {
        itemList.remove(item);
    }

    @Override
    public Item getItemByName(String name) {
        Item result = null;
        for (Item item : itemList) {
            if (item.getItemName().equals(name)) {
                result = item;
                break;
            }
        }
        return result;
    }
}
