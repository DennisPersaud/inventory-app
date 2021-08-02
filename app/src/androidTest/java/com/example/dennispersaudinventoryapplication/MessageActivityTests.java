package com.example.dennispersaudinventoryapplication;

import android.view.View;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class MessageActivityTests {
    private View decorView;

    @Rule
    public ActivityScenarioRule<MessageActivity> activityScenarioRule
            = new ActivityScenarioRule<>(MessageActivity.class);

    @Before
    public void setUp() {
        activityScenarioRule.getScenario().onActivity(activity -> decorView
                = activity.getWindow().getDecorView());
    }

    /*
     * Test if message activity is displayed
     */
    @Test
    public void test_isMessageActivityInView() {
        onView(withId(R.id.messageActivity)).check(matches(isDisplayed()));
    }
}
