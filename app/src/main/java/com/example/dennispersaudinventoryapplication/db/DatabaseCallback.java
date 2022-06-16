package com.example.dennispersaudinventoryapplication.db;

import static com.example.dennispersaudinventoryapplication.db.AppDatabase.getDatabaseWriterExecutor;

import androidx.annotation.NonNull;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.dennispersaudinventoryapplication.db.model.Item;
import com.example.dennispersaudinventoryapplication.db.model.ItemDao;
import com.example.dennispersaudinventoryapplication.db.model.User;
import com.example.dennispersaudinventoryapplication.db.model.UserDao;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import javax.inject.Provider;

public class DatabaseCallback extends RoomDatabase.Callback {

    final AppDatabase database;
    private ItemDao itemDao;
    private UserDao userDao;

    Item sampleItem;
    Item sampleItem2;
    User sampleUser;

    @Inject
    public DatabaseCallback(Provider<AppDatabase> databaseProvider){
        database = databaseProvider.get();
    }

    @Override
    public void onCreate(@NonNull @NotNull SupportSQLiteDatabase db) {
        super.onCreate(db);

        getDatabaseWriterExecutor().execute(() -> {

            itemDao = database.itemDao();
            sampleItem.setItemName("Book");
            sampleItem.setItemPrice(12);
            sampleItem.setItemCount(1);
            itemDao.insertItem(sampleItem);

            sampleItem2.setItemName("Gold Bars");
            sampleItem2.setItemPrice(1200);
            sampleItem2.setItemCount(1);
            itemDao.insertItem(sampleItem2);

            userDao = database.userDao();
            sampleUser.setUserName("SampleUser");
            sampleUser.setUserPassword("123456");
            userDao.insertUser(sampleUser);
        });
    }
}
