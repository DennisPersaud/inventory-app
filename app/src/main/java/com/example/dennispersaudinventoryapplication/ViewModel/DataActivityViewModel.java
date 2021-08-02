package com.example.dennispersaudinventoryapplication.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.dennispersaudinventoryapplication.Database.MainRepository;
import com.example.dennispersaudinventoryapplication.Models.Item;

import java.util.List;

public class DataActivityViewModel extends AndroidViewModel {

    private final MainRepository mRepo;

    private final LiveData<List<Item>> mAllItems;

    public DataActivityViewModel(Application application) {
        super(application);
        mRepo = new MainRepository(application);
        mAllItems = mRepo.getAllItems();
    }

    public void insertItem(Item item) { mRepo.insertItem(item);}

    public void updateItem(Item item) { mRepo.updateItem(item);}

    public void deleteItem(Item item) { mRepo.deleteItem(item);}

    public LiveData<List<Item>> getmAllItems() { return mAllItems; }
}
