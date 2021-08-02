package com.example.dennispersaudinventoryapplication;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openContextualActionModeOverflowMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;

@LargeTest
public class MainActivityTests {

    private static final String USERNAME_TO_BE_TYPED = "Espresso";
    private static final String PASSWORD_TO_BE_TYPED = "Espresso";
    private View decorView;

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule
            = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void setUp() {
        activityScenarioRule.getScenario().onActivity(activity -> decorView
                = activity.getWindow().getDecorView());
    }

    /*
     * Example instrumented test
     */
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.dennispersaudinventoryapplication", appContext.getPackageName());
    }

    /*
     * Test if main activity is displayed
     */
    @Test
    public void test_isActivityInView() {
        onView(withId(R.id.mainActivity)).check(matches(isDisplayed()));
    }

    /*
     * Test if title of login button is displayed
     */
    @Test
    public void test_visibility_title_loginButton() {
        onView(withId(R.id.buttonLogin)).check(matches(isDisplayed()));
    }

    /*
     * Test if login button was clicked
     */
    @Test
    public void test_clicked_loginButton() {
        onView(withId(R.id.buttonLogin)).perform(click());

    }

    /*
     * Test if title of create account button is displayed
     */
    @Test
    public void test_visibility_title_createAccountButton() {
        onView(withId(R.id.buttonCreateAccount)).check(matches(isDisplayed()));
    }

    /*
     * Test if create account button was clicked
     */
    @Test
    public void test_clicked_createAccountButton() {
        onView(withId(R.id.buttonCreateAccount)).perform(click());

    }

    /*
     * Test if username input hint is displayed
     */
    @Test
    public void test_visibility_usernameHint() {
        onView(withId(R.id.editTextUsername)).check(matches(withHint(R.string.hint_usernameInput)));
    }

    /*
     * Test if password input hint is displayed
     */
    @Test
    public void test_visibility_passwordHint() {
        onView(withId(R.id.editTextPassword)).check(matches(withHint(R.string.hint_passwordInput)));
    }

    /*
     * Test login button with no credentials
     */
    @Test
    public void test_loginNoCredentials_returnFailed() {
        onView(withId(R.id.buttonLogin)).perform(click());
        onView(withId(com.google.android.material.R.id.snackbar_text))
                .check(matches(isDisplayed()));
        onView(withId(com.google.android.material.R.id.snackbar_text))
                .check(matches(withText(R.string.toast_loginFailed)));
    }

    /*
     * Test login button with username only
     */
    @Test
    public void test_loginUsernameOnly_returnFailed() {
        // Type text and then press the button.
        onView(withId(R.id.editTextUsername))
                .perform(typeText(USERNAME_TO_BE_TYPED), closeSoftKeyboard());
        // Click button
        onView(withId(R.id.buttonLogin)).perform(click());
        // Check that the text was changed.
        onView(withId(com.google.android.material.R.id.snackbar_text))
                .check(matches(isDisplayed()));
        onView(withId(com.google.android.material.R.id.snackbar_text))
                .check(matches(withText(R.string.toast_loginFailed)));
    }

    /*
     * Test login button with password only
     */
    @Test
    public void test_loginPasswordOnly_returnFailed() {
        // Type text and then press the button.
        onView(withId(R.id.editTextPassword))
                .perform(typeText(PASSWORD_TO_BE_TYPED), closeSoftKeyboard());
        // Click button
        onView(withId(R.id.buttonLogin)).perform(click());
        // Check that the text was changed.
        onView(withId(com.google.android.material.R.id.snackbar_text))
                .check(matches(isDisplayed()));
        onView(withId(com.google.android.material.R.id.snackbar_text))
                .check(matches(withText(R.string.toast_loginFailed)));
    }

    /*
     * Test login button with username & password
     */
    @Test
    public void test_loginCredentials_returnSuccess() {
        // Type text and then press the button.
        onView(withId(R.id.editTextUsername))
                .perform(typeText(USERNAME_TO_BE_TYPED), closeSoftKeyboard());
        onView(withId(R.id.editTextPassword))
                .perform(typeText(PASSWORD_TO_BE_TYPED), closeSoftKeyboard());
        // Click button
        onView(withId(R.id.buttonLogin)).perform(click());
        // Check that the text was changed.
        onView(withId(R.id.dataActivity)).check(matches(isDisplayed()));
//        onView(withId(com.google.android.material.R.id.snackbar_text))
//                .check(matches(isDisplayed()));
//        onView(withId(com.google.android.material.R.id.snackbar_text))
//                .check(matches(withText(R.string.toast_loginSuccess)));
    }

    /*
     * Test create account button with no credentials
     */
    @Test
    public void test_createAccountNoCredentials_returnFailed() {
        // Click button
        onView(withId(R.id.buttonCreateAccount)).perform(click());
        // Check that the error was displayed.
        onView(withId(R.id.editTextUsername))
                .check(matches(hasErrorText("Enter a username.")));
        onView(withId(R.id.editTextPassword))
                .check(matches(hasErrorText("Enter a 6 character password.")));
    }

    /*
     * Test create account button with username only
     */
    @Test
    public void test_createAccountUsernameOnly_returnFailed() {
        // Given
        onView(withId(R.id.editTextUsername))
                .perform(typeText(USERNAME_TO_BE_TYPED), closeSoftKeyboard());
        // When
        onView(withId(R.id.buttonCreateAccount)).perform(click());
        // Then
        onView(withId(R.id.editTextUsername))
                .check(matches(hasErrorText("Enter a username.")));
        onView(withId(R.id.editTextPassword))
                .check(matches(hasErrorText("Enter a 6 character password.")));
    }

    /*
     * Test create account button with password only
     */
    @Test
    public void test_createAccountPasswordOnly_returnFailed() {
        // Given
        onView(withId(R.id.editTextPassword))
                .perform(typeText(PASSWORD_TO_BE_TYPED), closeSoftKeyboard());
        // When
        onView(withId(R.id.buttonCreateAccount)).perform(click());
        // Then
        onView(withId(R.id.editTextUsername))
                .check(matches(hasErrorText("Enter a username.")));
        onView(withId(R.id.editTextPassword))
                .check(matches(hasErrorText("Enter a 6 character password.")));
    }

    /*
     * Test create account with credentials
     */
    @Test
    public void test_createAccountCredentials_returnSuccess() {
        // Given
        onView(withId(R.id.editTextUsername))
                .perform(typeText(USERNAME_TO_BE_TYPED), closeSoftKeyboard());
        onView(withId(R.id.editTextPassword))
                .perform(typeText(PASSWORD_TO_BE_TYPED), closeSoftKeyboard());
        // When
        onView(withId(R.id.buttonCreateAccount)).perform(click());
        // Then
        onView(withId(com.google.android.material.R.id.snackbar_text))
                .check(matches(isDisplayed()));
        onView(withId(com.google.android.material.R.id.snackbar_text))
                .check(matches(withText(R.string.toast_createAccountSuccess)));
    }

    /*
     * Test create account short password error
     */
    @Test
    public void test_createAccountShortPassword_returnFail() {
        // Given
        onView(withId(R.id.editTextUsername))
                .perform(typeText("espresso"), closeSoftKeyboard());
        onView(withId(R.id.editTextPassword))
                .perform(typeText("123"), closeSoftKeyboard());
        // When
        onView(withId(R.id.buttonCreateAccount)).perform(click());
        // Then
        onView(withId(R.id.editTextPassword))
                .check(matches(hasErrorText("Enter a 6 character password.")));
    }

    /*
     * Test username already exists error
     */
    @Test
    public void test_createAccountUserExists_returnFail() {
        // Given
        onView(withId(R.id.editTextUsername))
                .perform(typeText("espresso"), closeSoftKeyboard());
        onView(withId(R.id.editTextPassword))
                .perform(typeText("123456"), closeSoftKeyboard());
        // When
        onView(withId(R.id.buttonCreateAccount)).perform(click());
        onView(withId(R.id.buttonCreateAccount)).perform(click());
        // Then
        onView(withId(R.id.editTextUsername))
                .check(matches(hasErrorText("Username already exists.")));
    }
}


/*
 * Test 3
 */
//    @Ignore
//    @Test
//    public void createAccount_newActivity() {
//        fail("Not implemented correctly");
//        // Given
//        onView(withId(R.id.editTextUsername))
//                .perform(typeText(USERNAME_TO_BE_TYPED), closeSoftKeyboard());
//        onView(withId(R.id.editTextPassword))
//                .perform(typeText(PASSWORD_TO_BE_TYPED), closeSoftKeyboard());
//        // When
//        onView(withId(R.id.buttonLogin)).perform(click());
//        // Then
//        onView(withId(R.id.editTextUsername))
//                .check(matches(withText(USERNAME_TO_BE_TYPED)));
//    }
