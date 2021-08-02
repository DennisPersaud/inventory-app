package com.example.dennispersaudinventoryapplication.Database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.dennispersaudinventoryapplication.Models.Item;
import com.example.dennispersaudinventoryapplication.Models.User;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class MainRepository {
    private final UserDao userDao;
    private final ItemDao itemDao;
    private final LiveData<List<Item>> allItems;

    public MainRepository(Application application) {
        MainDatabase db = MainDatabase.getInstance(application);
        userDao = db.userDao();
        itemDao = db.itemDao();
        allItems = itemDao.loadAllItems();
    }

    /*
     * Methods for item DAO
     */
    public void insertItem(Item item) {
        MainDatabase.databaseWriterExecutor.execute(() -> itemDao.insertItem(item));
    }

    public void updateItem(Item item) {
        MainDatabase.databaseWriterExecutor.execute(() -> itemDao.updateItem(item));
    }

    public void deleteItem(Item item) {
        MainDatabase.databaseWriterExecutor.execute(() -> itemDao.deleteItem(item));
    }

    public LiveData<List<Item>> getAllItems() {
        return allItems;
    }

    /*
     * Methods for user DAO
     */
    public void insertUser(User user) {
        MainDatabase.databaseWriterExecutor.execute(() -> userDao.insertUser(user));
    }

    public void updateUser(User user) {
        MainDatabase.databaseWriterExecutor.execute(() -> userDao.updateUser(user));
    }

    public void deleteUser(User user) {
        MainDatabase.databaseWriterExecutor.execute(() -> userDao.deleteUser(user));
    }

    public List<User> getAllUsers() throws ExecutionException, InterruptedException {
        Future<List<User>> future = MainDatabase.databaseWriterExecutor.submit(userDao::getAllUsers);
        return future.get();
    }

    public User getUserByName(String name) throws ExecutionException, InterruptedException {
        Future<User> future = MainDatabase.databaseWriterExecutor.submit(() -> userDao.getUserByName(name));
        return future.get();
    }

    public String getPasswordByName(String name) throws ExecutionException, InterruptedException {
        Future<String> future = MainDatabase.databaseWriterExecutor.submit(() -> userDao.getPasswordByName(name));
        return future.get();
    }

    public String getUsernameByName(String name) throws ExecutionException, InterruptedException {
        Future<String> future = MainDatabase.databaseWriterExecutor.submit(() -> userDao.getUsernameByName(name));
        return future.get();
    }



//    public LiveData<User> getUser(String username, String password) {
//        return userDao.getUser(username, password);
//    }

//    public List<User> loadAllUsers() throws ExecutionException, InterruptedException {
//        Future<List<User>> future = MainDatabase.databaseWriterExecutor.submit(userDao::loadAllUsers);
//        return future.get();
//    }
}