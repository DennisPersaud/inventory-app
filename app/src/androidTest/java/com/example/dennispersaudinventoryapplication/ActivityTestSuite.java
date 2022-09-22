package com.example.dennispersaudinventoryapplication;

import com.example.dennispersaudinventoryapplication.feature_inventory.data.local.dao.ItemDaoTest;
import com.example.dennispersaudinventoryapplication.feature_inventory.data.local.dao.UserDaoTest;
import com.example.dennispersaudinventoryapplication.feature_inventory.presentation.ui.MainActivityTests;
import com.example.dennispersaudinventoryapplication.feature_inventory.presentation.inventory.DataFragment;
import com.example.dennispersaudinventoryapplication.feature_inventory.presentation.settings.MessageFragment;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        MainActivityTests.class,
        DataFragment.class,
        MessageFragment.class,
        UserDaoTest.class,
        ItemDaoTest.class
})
public class ActivityTestSuite {
}