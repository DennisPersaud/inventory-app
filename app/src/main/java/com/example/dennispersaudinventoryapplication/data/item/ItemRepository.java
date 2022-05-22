package com.example.dennispersaudinventoryapplication.data.item;

import androidx.lifecycle.LiveData;

import com.example.dennispersaudinventoryapplication.data.AppDatabase;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.inject.Inject;

public class ItemRepository {
    private final ItemDao itemDao;
    private final LiveData<List<Item>> allItems;

    @Inject
    public ItemRepository(ItemDao itemDao) {
        this.itemDao = itemDao;
        allItems = itemDao.loadAllItems();
    }


    /*
     * Methods for item DAO
     */
    public void insertItem(Item item) {
        AppDatabase.getDatabaseWriterExecutor().execute(() -> itemDao.insertItem(item));
    }

    public void updateItem(Item item) {
        AppDatabase.getDatabaseWriterExecutor().execute(() -> itemDao.updateItem(item));
    }

    public void deleteItem(Item item) {
        AppDatabase.getDatabaseWriterExecutor().execute(() -> itemDao.deleteItem(item));
    }

    public LiveData<List<Item>> loadAllItems() {
        return allItems;
    }

    public List<Item> getAllItems() throws ExecutionException, InterruptedException {
        Future<List<Item>> future = AppDatabase.getDatabaseWriterExecutor().submit(itemDao::getAllItems);
        return future.get();
    }

    public Item getItemByName(String name) throws ExecutionException, InterruptedException {
        Future<Item> future = AppDatabase.getDatabaseWriterExecutor().submit(() -> itemDao.getItemByName(name));
        return future.get();
    }

}
