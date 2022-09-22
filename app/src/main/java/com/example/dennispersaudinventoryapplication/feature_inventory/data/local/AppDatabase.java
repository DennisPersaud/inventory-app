package com.example.dennispersaudinventoryapplication.feature_inventory.data.local;

import androidx.room.RoomDatabase;

import com.example.dennispersaudinventoryapplication.feature_inventory.domain.model.Item;
import com.example.dennispersaudinventoryapplication.feature_inventory.data.local.dao.ItemDao;
import com.example.dennispersaudinventoryapplication.feature_inventory.domain.model.User;
import com.example.dennispersaudinventoryapplication.feature_inventory.data.local.dao.UserDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@androidx.room.Database(entities =  {User.class, Item.class}, version = 1, exportSchema = false)
// TODO: Remove public access modifiers after testing
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract ItemDao itemDao();

    private static volatile AppDatabase INSTANCE = null;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriterExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AppDatabase getInstance(){
        return INSTANCE;
    }

    public static void setInstance(AppDatabase db) {
        INSTANCE = db;
    }

    public static ExecutorService getDatabaseWriterExecutor() {
        return databaseWriterExecutor;
    }
}
