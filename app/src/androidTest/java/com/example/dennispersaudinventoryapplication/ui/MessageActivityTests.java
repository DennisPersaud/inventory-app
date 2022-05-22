package com.example.dennispersaudinventoryapplication.ui;

import android.view.View;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import com.example.dennispersaudinventoryapplication.R;
import com.example.dennispersaudinventoryapplication.ui.fragment.MessageFragment;

public class MessageActivityTests {
    private View decorView;

    @Rule
    public ActivityScenarioRule<MessageFragment> activityScenarioRule
            = new ActivityScenarioRule<>(MessageFragment.class);

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
        onView(ViewMatchers.withId(R.id.messageActivity)).check(matches(isDisplayed()));
    }
}
