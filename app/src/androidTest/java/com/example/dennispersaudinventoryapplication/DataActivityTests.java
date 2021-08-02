package com.example.dennispersaudinventoryapplication;

import android.view.View;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import java.lang.reflect.WildcardType;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.fail;


public class DataActivityTests {
    private View decorView;

    @Rule
    public ActivityScenarioRule<DataActivity> activityScenarioRule
            = new ActivityScenarioRule<>(DataActivity.class);

    @Before
    public void setUp() throws Exception {
        activityScenarioRule.getScenario().onActivity(activity -> decorView
                = activity.getWindow().getDecorView());
    }

    /*
     * Test if data activity is displayed
     */
    @Test
    public void test_isDataActivityInView() {
        onView(withId(R.id.dataActivity)).check(matches(isDisplayed()));
    }
}