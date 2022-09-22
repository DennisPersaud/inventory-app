package com.example.dennispersaudinventoryapplication.feature_inventory.presentation.vm;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.dennispersaudinventoryapplication.feature_inventory.domain.model.Item;
import com.example.dennispersaudinventoryapplication.feature_inventory.domain.repository.ItemRepository;
import com.example.dennispersaudinventoryapplication.feature_inventory.utils.Event;
import com.example.dennispersaudinventoryapplication.feature_inventory.utils.Resource;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class DataActivityViewModel extends AndroidViewModel {

    private final ItemRepository itemRepo;

    private final LiveData<List<Item>> mAllItems;

    private final MutableLiveData<Event<Resource<Item>>> _insertItemStatus = new MutableLiveData<>();
    LiveData<Event<Resource<Item>>> insertItemStatus = _insertItemStatus;

    @Inject
    public DataActivityViewModel(Application application, ItemRepository itemRepo) {
        super(application);
        this.itemRepo = itemRepo;
        mAllItems = itemRepo.loadAllItems();
    }

    public void insertItem(Item item) { itemRepo.insertItem(item);}

    public void updateItem(Item item) { itemRepo.updateItem(item);}

    public void deleteItem(Item item) { itemRepo.deleteItem(item);}

    public LiveData<List<Item>> loadAllItems() { return mAllItems; }

//    public List<Item> getAllItems() throws ExecutionException, InterruptedException { return itemRepo.getAllItems(); }

    public Item getItemByName(String name) throws ExecutionException, InterruptedException { return itemRepo.getItemByName(name); }
}
