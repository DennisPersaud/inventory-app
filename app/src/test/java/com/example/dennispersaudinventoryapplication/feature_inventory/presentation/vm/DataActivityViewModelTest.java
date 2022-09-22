package com.example.dennispersaudinventoryapplication.feature_inventory.presentation.vm;

import com.example.dennispersaudinventoryapplication.feature_inventory.domain.model.Item;
import com.example.dennispersaudinventoryapplication.respoitories.FakeItemRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DataActivityViewModelTest {

    private DataActivityViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new DataActivityViewModel(viewModel.getApplication(), new FakeItemRepository());
    }

    @After
    public void tearDown() {
    }

    @Test
    public void insertItem() {
        viewModel.insertItem(new Item("book", 1, 2));
    }

    @Test
    public void updateItem() {
    }

    @Test
    public void deleteItem() {
    }

    @Test
    public void loadAllItems() {
    }

    @Test
    public void getItemByName() {
    }
}