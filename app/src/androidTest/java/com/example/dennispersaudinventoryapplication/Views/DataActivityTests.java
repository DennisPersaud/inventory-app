package com.example.dennispersaudinventoryapplication.Views;

import android.view.View;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withChild;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withTagValue;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.junit.Assert.fail;

import com.example.dennispersaudinventoryapplication.R;
import com.example.dennispersaudinventoryapplication.Utils.GridAdapter;
import com.example.dennispersaudinventoryapplication.Views.DataActivity;


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

//    private static DataInteraction onRow(String str) {
//        return onData(hasEntry(equalTo()))
//    }

    /*
     * Test if data activity is displayed
     */
    @Test
    public void test_isDataActivityInView() {
        onView(ViewMatchers.withId(R.id.dataActivity)).check(matches(isDisplayed()));
    }

    /*
     * Test if grid view is displayed
     */
    @Test
    public void test_isGridViewInView() {
        onView(withId(R.id.gridView)).check(matches(isDisplayed()));
    }

    /*
     * Test if data activity is displayed
     */
    @Test
    public void test_isGridItemNameInView() {
//        onView(withId(R.id.gridItemName)).check(matches(isDisplayed()));
        onData(withId(R.id.gridItemName))
                .inAdapterView(withId(R.id.gridView))
                .atPosition(0)
                .check(matches(isDisplayed()));
    }

    /*
     * Test if data activity is displayed
     */
    @Test
    public void test_isGridItemCountInView() {
//        onView(withId(R.id.gridItemCount)).check(matches(isDisplayed()));
        onData(withText("Book"))
                .inAdapterView(withId(R.id.gridView))
                .atPosition(0)
                .onChildView(withId(R.id.gridItemName))
                .check(matches(isDisplayed()));
    }

    /*
     * Test if data activity is displayed
     */
    @Test
    public void test_isGridItemPriceInView() {
//        onView(withId(R.id.gridItemPrice)).check(matches(isDisplayed()));
        onData(withId(R.id.gridItemPrice))
                .inAdapterView(withId(R.id.gridView))
                .atPosition(0)
                .check(matches(isDisplayed()));
    }

    /*
     * Test if data activity is displayed
     */
    @Test
    public void test_isFABInView() {
        onView(withId(R.id.fab_addItem)).check(matches(isDisplayed()));
    }

}