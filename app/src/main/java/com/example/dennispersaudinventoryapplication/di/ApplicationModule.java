package com.example.dennispersaudinventoryapplication.di;

import android.app.Application;

import androidx.room.Room;

import com.example.dennispersaudinventoryapplication.data.DatabaseCallback;
import com.example.dennispersaudinventoryapplication.data.item.Item;
import com.example.dennispersaudinventoryapplication.data.item.ItemDao;
import com.example.dennispersaudinventoryapplication.data.AppDatabase;
import com.example.dennispersaudinventoryapplication.data.user.User;
import com.example.dennispersaudinventoryapplication.data.user.UserDao;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class ApplicationModule {

    @Provides
    @Singleton
    public AppDatabase provideDatabase(Application app, DatabaseCallback callback) {
        synchronized (AppDatabase.class) {
            if (AppDatabase.getInstance() == null) {
                AppDatabase.setInstance(
                Room.databaseBuilder(app.getApplicationContext(),
                                AppDatabase.class, "main_database")
                        .addCallback(callback)
                        .build()
                );
            }
        }
        return AppDatabase.getInstance();
    }

    @Provides
    public UserDao provideUserDao(AppDatabase database) {
        return database.userDao();
    }

    @Provides
    public ItemDao provideItemDao(AppDatabase database) {
        return database.itemDao();
    }
}