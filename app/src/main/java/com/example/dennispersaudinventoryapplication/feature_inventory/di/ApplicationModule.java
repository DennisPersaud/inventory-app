package com.example.dennispersaudinventoryapplication.feature_inventory.di;

import android.content.Context;

import androidx.room.Room;

import com.example.dennispersaudinventoryapplication.feature_inventory.data.local.AppDatabase;
import com.example.dennispersaudinventoryapplication.feature_inventory.data.local.DatabaseCallback;
import com.example.dennispersaudinventoryapplication.feature_inventory.data.local.dao.ItemDao;
import com.example.dennispersaudinventoryapplication.feature_inventory.data.local.dao.UserDao;

import javax.inject.Provider;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class ApplicationModule {

    @Singleton
    @Provides
    public AppDatabase provideDatabase(@ApplicationContext Context context, Provider<AppDatabase> provider) {
        synchronized (AppDatabase.class) {
            if (AppDatabase.getInstance() == null) {
                AppDatabase.setInstance(
                Room.databaseBuilder(context,
                                AppDatabase.class, "main_database")
                        .addCallback(new DatabaseCallback(provider))
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