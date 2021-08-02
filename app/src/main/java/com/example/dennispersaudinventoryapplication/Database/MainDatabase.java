package com.example.dennispersaudinventoryapplication.Database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.dennispersaudinventoryapplication.Models.Item;
import com.example.dennispersaudinventoryapplication.Models.User;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@androidx.room.Database(entities =  {User.class, Item.class}, version = 1, exportSchema = false)
// TODO: Remove public access modifiers after testing
public abstract class MainDatabase extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract ItemDao itemDao();

    private static volatile MainDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriterExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static MainDatabase getInstance(final Context context) {
        synchronized (MainDatabase.class) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        MainDatabase.class, "main_database")
                        .addCallback(sRoomDatabaseCallback)
                        .build();
            }
        }
        return INSTANCE;
    }

    private static final RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull @NotNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriterExecutor.execute(() -> {
//                ItemDao itemDao = INSTANCE.itemDao();

                UserDao userDao = INSTANCE.userDao();
                User sampleUser = new User("SampleUser", "123");
                userDao.insertUser(sampleUser);
            });
        }
    };
}
