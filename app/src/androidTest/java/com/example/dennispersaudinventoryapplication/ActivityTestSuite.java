package com.example.dennispersaudinventoryapplication;

import com.example.dennispersaudinventoryapplication.Models.ItemDaoTests;
import com.example.dennispersaudinventoryapplication.Models.UserDaoTests;
import com.example.dennispersaudinventoryapplication.Views.DataActivityTests;
import com.example.dennispersaudinventoryapplication.Views.MainActivityTests;
import com.example.dennispersaudinventoryapplication.Views.MessageActivityTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        MainActivityTests.class,
        DataActivityTests.class,
        MessageActivityTests.class,
        UserDaoTests.class,
        ItemDaoTests.class
})
public class ActivityTestSuite {
}