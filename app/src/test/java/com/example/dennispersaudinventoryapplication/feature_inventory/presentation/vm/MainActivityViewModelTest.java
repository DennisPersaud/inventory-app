package com.example.dennispersaudinventoryapplication.feature_inventory.presentation.vm;

import com.example.dennispersaudinventoryapplication.respoitories.FakeUserRepository;

import org.junit.Before;
import org.junit.Test;

public class MainActivityViewModelTest {

    private MainActivityViewModel viewModel;


    @Before
    public void setUp() {
        viewModel = new MainActivityViewModel(viewModel.getApplication(), new FakeUserRepository());
    }

    @Test
    public void insertUser() {
    }

    @Test
    public void getPasswordByName() {
    }

    @Test
    public void getUsernameByName() {
    }
}