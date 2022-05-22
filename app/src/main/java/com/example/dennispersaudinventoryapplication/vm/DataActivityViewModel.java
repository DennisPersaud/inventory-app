package com.example.dennispersaudinventoryapplication.vm;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.dennispersaudinventoryapplication.data.item.Item;
import com.example.dennispersaudinventoryapplication.data.item.ItemRepository;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import dagger.hilt.android.qualifiers.ApplicationContext;

@HiltViewModel
public class DataActivityViewModel extends AndroidViewModel {

    private final ItemRepository itemRepo;

    private final LiveData<List<Item>> mAllItems;

    @Inject
    public DataActivityViewModel(Application application, ItemRepository itemRepo) {
        super(application);
        this.itemRepo = itemRepo;
//        mRepo = new MainRepository(application);
        mAllItems = itemRepo.loadAllItems();
    }

    public void insertItem(Item item) { itemRepo.insertItem(item);}

    public void updateItem(Item item) { itemRepo.updateItem(item);}

    public void deleteItem(Item item) { itemRepo.deleteItem(item);}

    public LiveData<List<Item>> loadAllItems() { return mAllItems; }

    public List<Item> getAllItems() throws ExecutionException, InterruptedException { return itemRepo.getAllItems(); }

    public Item getItemByName(String name) throws ExecutionException, InterruptedException { return itemRepo.getItemByName(name); }
}
