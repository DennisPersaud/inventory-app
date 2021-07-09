package com.example.dennispersaudinventoryapplication;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        MainActivityTest.class,
        DataActivityTest.class,
        MessageActivityTest.class
})
public class ActivityTestSuite {
}