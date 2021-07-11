package com.example.dennispersaudinventoryapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.dennispersaudinventoryapplication.Models.Item;
import com.example.dennispersaudinventoryapplication.Models.User;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // User table constants
    private static final String USER_TABLE = "USER_TABLE";
    private static final String COLUMN_USER_ID = "USER_ID";
    private static final String COLUMN_USER_NAME = "USER_NAME";
    private static final String COLUMN_USER_PASSWORD = "USER_PASSWORD";

    // Item table constants
    private static final String ITEM_TABLE = "ITEM_TABLE";
    private static final String COLUMN_ITEM_NAME = "ITEM_NAME";
    private static final String COLUMN_ITEM_SKU = "ITEM_SKU";
    private static final String COLUMN_ITEM_PRICE = "ITEM_PRICE";
    private static final String COLUMN_ITEM_COUNT = "ITEM_COUNT";

    // Table create statements
    private static final String CREATE_TABLE_USER = "CREATE TABLE " + USER_TABLE +
            " (" + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USER_NAME +
            " TEXT, " + COLUMN_USER_PASSWORD + " TEXT)";
    private static final String CREATE_TABLE_ITEM = "CREATE TABLE " + ITEM_TABLE +
            " (" + COLUMN_ITEM_SKU + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_ITEM_NAME +
            " TEXT, " + COLUMN_ITEM_PRICE + " INTEGER, " + COLUMN_ITEM_COUNT + " INTEGER)";

    // Constructor
    public DatabaseHelper(Context context) {
        super(context, "user.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create tables
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_ITEM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // On upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + ITEM_TABLE);

        // Create new tables
        onCreate(db);
    }

    // Add new user to user table in database
    public boolean addUser(User userData) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_USER_NAME, userData.getUserName());
        cv.put(COLUMN_USER_PASSWORD, userData.getUserPassword());

        long insert = db.insert(USER_TABLE, null, cv);
        db.close();
        return insert != -1;
    }

    // Check if username exists
    public Boolean checkUsername(User userData) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + USER_TABLE + " WHERE " +
                COLUMN_USER_NAME + " = ?", new String[]{userData.getUserName()});

        if (cursor.getCount() > 0) {
            cursor.close();
            db.close();
            return true;
        } else {
            cursor.close();
            db.close();
            return false;
        }
    }

    // Check if username and password match database
    public Boolean checkUsernamePassword(User userData) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + USER_TABLE + " WHERE " +
                COLUMN_USER_NAME + " = ? AND " + COLUMN_USER_PASSWORD + " = ?",
                new String[]{userData.getUserName(), userData.getUserPassword()});

        if (cursor.getCount() > 0) {
            cursor.close();
            db.close();
            return true;
        } else {
            cursor.close();
            db.close();
            return false;
        }
    }

    // Get all items from the database
    public List<Item> getAllItems() {
        List<Item> itemList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT * FROM " + ITEM_TABLE;

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                String itemName = cursor.getString(1);
                int itemPrice = cursor.getInt(2);
                int itemCount = cursor.getInt(3);

                Item newItem = new Item(itemName, itemPrice, itemCount);
                itemList.add(newItem);

            } while (cursor.moveToNext());
        } else {
            // Failure, do not add anything to list.
        }
        cursor.close();
        db.close();
        return itemList;
    }

    // Add item to database
    public boolean addItem(Item itemData) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ITEM_NAME, itemData.getItemName());
        cv.put(COLUMN_ITEM_PRICE, itemData.getItemPrice());
        cv.put(COLUMN_ITEM_COUNT, itemData.getItemCount());

        long insert = db.insert(ITEM_TABLE, null, cv);
        db.close();
        return insert != -1;
    }

    // Delete item from database
    public boolean deleteItem(Item itemData) {
        SQLiteDatabase db = this.getWritableDatabase();

        long delete = db.delete(ITEM_TABLE, COLUMN_ITEM_NAME + " = ?",
                new String[]{String.valueOf(itemData.getItemName())});
        db.close();
        return delete != -1;
    }

    // Update count for item name in database
    public boolean updateItem(Item itemData) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ITEM_COUNT, itemData.getItemCount());

        long update = db.update(ITEM_TABLE, cv, COLUMN_ITEM_NAME + " = ?",
                new String[]{String.valueOf(itemData.getItemName())});
        db.close();
        return update != -1;
    }
}