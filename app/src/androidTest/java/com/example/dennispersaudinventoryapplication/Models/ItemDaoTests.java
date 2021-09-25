package com.example.dennispersaudinventoryapplication.Models;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.room.RoomWarnings;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.dennispersaudinventoryapplication.Database.ItemDao;
import com.example.dennispersaudinventoryapplication.Database.MainDatabase;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.concurrent.ExecutionException;


@RunWith(AndroidJUnit4.class)
public class ItemDaoTests extends TestCase {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    private MainDatabase db;
    private ItemDao dao;

    @Before
    public void createDb() {
        this.db = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().getContext(),
                MainDatabase.class)
                .allowMainThreadQueries()
                .build();
        dao = db.itemDao();
    }

    @After
    public void closeDb() {
        db.close();
    }

    @Test
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    public void test_insertItem_matchItemName() throws ExecutionException, InterruptedException {

        // Insert item into database
        Item item = new Item("testItem", 1, 3);
        dao.insertItem(item);

        // Get items from database
        Item insertedItem = dao.getItemByName("testItem");

        // Assert item name inserted matches db item name
        assertThat(insertedItem.getItemName(), equalTo(item.getItemName()));
    }

    @Test
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    public void test_updateItem_ReturnTrue() throws ExecutionException, InterruptedException {

        // Insert item into database
        Item item = new Item("testItem", 1, 3);
        dao.insertItem(item);

        // Get inserted item & update name
        Item updateItem = dao.getItemByName("testItem");
        updateItem.setItemName("updatedItemName");
        dao.updateItem(updateItem);

        // Get items from database
        List<Item> insertedItems = null;
        try {
            insertedItems = dao.getAllItems();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        // Assert item inserted matches db item
        assertThat(insertedItems.get(0).getItemName(), equalTo(updateItem.getItemName()));
    }

    @Test
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    public void test_deleteItem_ReturnTrue() throws ExecutionException, InterruptedException {

        // Insert item into database
        Item item = new Item("testItem", 1, 3);
        dao.insertItem(item);

        // Get & delete item from database
        Item deleteItem = dao.getItemByName("testItem");
        dao.deleteItem(deleteItem);

        // Get inserted item from database
        List<Item> insertedItems = null;
        try {
            insertedItems = dao.getAllItems();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        // Assert item inserted matches db item
        assertThat(insertedItems.isEmpty(), is(true));
    }
}