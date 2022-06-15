package com.example.dennispersaudinventoryapplication.ui;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.view.View;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.dennispersaudinventoryapplication.R;
import com.example.dennispersaudinventoryapplication.ui.fragment.DataFragment;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


public class DataActivityTests {
//    @Rule
//    public ActivityScenarioRule<DataFragment> activityScenarioRule
//            = new ActivityScenarioRule<>(DataFragment.class);
//    private View decorView;
//
//    @Before
//    public void setUp() throws Exception {
//        activityScenarioRule.getScenario().onActivity(activity -> decorView
//                = activity.getWindow().getDecorView());
//    }
//
////    private static DataInteraction onRow(String str) {
////        return onData(hasEntry(equalTo()))
////    }
//
//    /*
//     * Test if data activity is displayed
//     */
//    @Test
//    public void test_isDataActivityInView() {
//        onView(ViewMatchers.withId(R.id.dataActivity)).check(matches(isDisplayed()));
//    }
//
//    /*
//     * Test if recycler view is displayed
//     */
//    @Test
//    public void test_isGridViewInView() {
//        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()));
//    }
//
//    /*
//     * Test if fab is displayed
//     */
//    @Test
//    public void test_isFABInView() {
//        onView(withId(R.id.fab_addItem)).check(matches(isDisplayed()));
//    }
//
//    /*
//     * Test if data activity is displayed
//     */
//    @Test
//    public void test_isGridItemNameInView() {
////        onView(withId(R.id.gridItemName)).check(matches(isDisplayed()));
//        onData(withId(R.id.gridItemName))
//                .inAdapterView(withId(R.id.recyclerView))
//                .atPosition(0)
//                .check(matches(isDisplayed()));
//    }
//
//    /*
//     * Test if data activity is displayed
//     */
//    @Test
//    public void test_isGridItemCountInView() {
////        onView(withId(R.id.gridItemCount)).check(matches(isDisplayed()));
//        onData(withText("Book"))
//                .inAdapterView(withId(R.id.recyclerView))
//                .atPosition(0)
//                .onChildView(withId(R.id.gridItemName))
//                .check(matches(isDisplayed()));
//    }
//
//    /*
//     * Test if data activity is displayed
//     */
//    @Test
//    public void test_isGridItemPriceInView() {
////        onView(withId(R.id.gridItemPrice)).check(matches(isDisplayed()));
//        onData(withId(R.id.gridItemPrice))
//                .inAdapterView(withId(R.id.recyclerView))
//                .atPosition(0)
//                .check(matches(isDisplayed()));
//    }
//

}