package com.example.dennispersaudinventoryapplication.feature_inventory.data.repository;

import androidx.lifecycle.LiveData;

import com.example.dennispersaudinventoryapplication.feature_inventory.data.local.AppDatabase;
import com.example.dennispersaudinventoryapplication.feature_inventory.domain.model.Item;
import com.example.dennispersaudinventoryapplication.feature_inventory.data.local.dao.ItemDao;
import com.example.dennispersaudinventoryapplication.feature_inventory.domain.repository.ItemRepository;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.inject.Inject;

public class ItemRepositoryImpl implements ItemRepository {
    private final ItemDao itemDao;
    private final LiveData<List<Item>> allItems;

    @Inject
    public ItemRepositoryImpl(ItemDao itemDao) {
        this.itemDao = itemDao;
        allItems = itemDao.loadAllItems();
    }

    @Override
    public void insertItem(Item item) {
        AppDatabase.getDatabaseWriterExecutor().execute(() -> itemDao.insertItem(item));
    }

    @Override
    public void updateItem(Item item) {
        AppDatabase.getDatabaseWriterExecutor().execute(() -> itemDao.updateItem(item));
    }

    @Override
    public void deleteItem(Item item) {
        AppDatabase.getDatabaseWriterExecutor().execute(() -> itemDao.deleteItem(item));
    }

    @Override
    public LiveData<List<Item>> loadAllItems() {
        return allItems;
    }

    @Override
    public Item getItemByName(String name) throws ExecutionException, InterruptedException {
        Future<Item> future = AppDatabase.getDatabaseWriterExecutor().submit(() -> itemDao.getItemByName(name));
        return future.get();
    }

}

    /*
     * Methods for item DAO
     */
//    public LiveData<List<Item>> loadAllItems() {
//        return allItems;
//    }
//
//
//    public Item getItemByName(String name) throws ExecutionException, InterruptedException {
//        Future<Item> future = AppDatabase.getDatabaseWriterExecutor().submit(() -> itemDao.getItemByName(name));
//        return future.get();
//    }

/*
    public List<Item> getAllItems() throws ExecutionException, InterruptedException {
        Future<List<Item>> future = AppDatabase.getDatabaseWriterExecutor().submit(itemDao::getAllItems);
        return future.get();
    }
*/
