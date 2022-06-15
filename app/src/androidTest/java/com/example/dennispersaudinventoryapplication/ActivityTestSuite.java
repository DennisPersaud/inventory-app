package com.example.dennispersaudinventoryapplication;

import com.example.dennispersaudinventoryapplication.db.model.ItemDaoTest;
import com.example.dennispersaudinventoryapplication.db.model.UserDaoTest;
import com.example.dennispersaudinventoryapplication.ui.DataActivityTests;
import com.example.dennispersaudinventoryapplication.ui.MainActivityTests;
import com.example.dennispersaudinventoryapplication.ui.MessageActivityTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        MainActivityTests.class,
        DataActivityTests.class,
        MessageActivityTests.class,
        UserDaoTest.class,
        ItemDaoTest.class
})
public class ActivityTestSuite {
}