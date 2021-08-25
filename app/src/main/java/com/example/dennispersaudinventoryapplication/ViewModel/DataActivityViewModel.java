package com.example.dennispersaudinventoryapplication.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.dennispersaudinventoryapplication.Database.MainRepository;
import com.example.dennispersaudinventoryapplication.Models.Item;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class DataActivityViewModel extends AndroidViewModel {

    private final MainRepository mRepo;

    private final LiveData<List<Item>> mAllItems;

    public DataActivityViewModel(Application application) {
        super(application);
        mRepo = new MainRepository(application);
        mAllItems = mRepo.loadAllItems();
    }

    public void insertItem(Item item) { mRepo.insertItem(item);}

    public void updateItem(Item item) { mRepo.updateItem(item);}

    public void deleteItem(Item item) { mRepo.deleteItem(item);}

    public LiveData<List<Item>> loadAllItems() { return mAllItems; }

    public List<Item> getAllItems() throws ExecutionException, InterruptedException { return mRepo.getAllItems(); }

    public Item getItemByName(String name) throws ExecutionException, InterruptedException { return mRepo.getItemByName(name); }
}
