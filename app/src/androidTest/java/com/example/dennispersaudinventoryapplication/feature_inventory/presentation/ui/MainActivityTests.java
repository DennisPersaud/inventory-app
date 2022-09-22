package com.example.dennispersaudinventoryapplication.feature_inventory.presentation.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

import android.content.Context;
import android.view.View;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.dennispersaudinventoryapplication.R;
import com.example.dennispersaudinventoryapplication.feature_inventory.presentation.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTests {

    private static final String USERNAME_TO_BE_TYPED = "Espresso";
    private static final String PASSWORD_TO_BE_TYPED = "Espresso";

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule
            = new ActivityScenarioRule<>(MainActivity.class);
    private View decorView;

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
        onView(ViewMatchers.withId(R.id.loginFragment)).check(matches(isDisplayed()));
    }

    /*
     * Test if login button is displayed
     */
    @Test
    public void test_visibility_button_login() {
        onView(withId(R.id.buttonLogin)).check(matches(isDisplayed()));
    }

    /*
     * Test if create account button is displayed
     */
    @Test
    public void test_visibility_button_createAccount() {
        onView(withId(R.id.buttonCreateAccount)).check(matches(isDisplayed()));
    }


    /*
     * Test if edit text username is displayed
     */
    @Test
    public void test_visibility_editText_username() {
        onView(withId(R.id.et_username)).check(matches(isDisplayed()));
    }

    /*
     * Test if edit text password is displayed
     */
    @Test
    public void test_visibility_editText_password() {
        onView(withId(R.id.et_password)).check(matches(isDisplayed()));
    }

    /*
     * Test if username input hint is displayed
     */
    @Test
    public void test_visibility_usernameHint() {
        onView(withId(R.id.et_username)).check(matches(withHint(R.string.hint_usernameInput)));
    }

    /*
     * Test if password input hint is displayed
     */
    @Test
    public void test_visibility_passwordHint() {
        onView(withId(R.id.et_password)).check(matches(withHint(R.string.hint_passwordInput)));
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
        onView(withId(R.id.et_username))
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
        onView(withId(R.id.et_password))
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
        onView(withId(R.id.et_username))
                .perform(typeText(USERNAME_TO_BE_TYPED), closeSoftKeyboard());
        onView(withId(R.id.et_password))
                .perform(typeText(PASSWORD_TO_BE_TYPED), closeSoftKeyboard());
        // Click button
        onView(withId(R.id.buttonLogin)).perform(click());
        // Check that the text was changed.
        onView(withId(R.id.dataFragment)).check(matches(isDisplayed()));
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
        onView(withId(R.id.et_username))
                .check(matches(hasErrorText("Enter a username.")));
        onView(withId(R.id.et_password))
                .check(matches(hasErrorText("Enter a 6 character password.")));
    }

    /*
     * Test create account button with username only
     */
    @Test
    public void test_createAccountUsernameOnly_returnFailed() {
        // Given
        onView(withId(R.id.et_username))
                .perform(typeText(USERNAME_TO_BE_TYPED), closeSoftKeyboard());
        // When
        onView(withId(R.id.buttonCreateAccount)).perform(click());
        // Then
        onView(withId(R.id.et_username))
                .check(matches(hasErrorText("Enter a username.")));
        onView(withId(R.id.et_password))
                .check(matches(hasErrorText("Enter a 6 character password.")));
    }

    /*
     * Test create account button with password only
     */
    @Test
    public void test_createAccountPasswordOnly_returnFailed() {
        // Given
        onView(withId(R.id.et_password))
                .perform(typeText(PASSWORD_TO_BE_TYPED), closeSoftKeyboard());
        // When
        onView(withId(R.id.buttonCreateAccount)).perform(click());
        // Then
        onView(withId(R.id.et_username))
                .check(matches(hasErrorText("Enter a username.")));
        onView(withId(R.id.et_password))
                .check(matches(hasErrorText("Enter a 6 character password.")));
    }

    /*
     * Test create account with credentials
     */
    @Test
    public void test_createAccountCredentials_returnSuccess() {
        // Given
        onView(withId(R.id.et_username))
                .perform(typeText(USERNAME_TO_BE_TYPED), closeSoftKeyboard());
        onView(withId(R.id.et_password))
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
        onView(withId(R.id.et_username))
                .perform(typeText("espresso"), closeSoftKeyboard());
        onView(withId(R.id.et_password))
                .perform(typeText("123"), closeSoftKeyboard());
        // When
        onView(withId(R.id.buttonCreateAccount)).perform(click());
        // Then
        onView(withId(R.id.et_password))
                .check(matches(hasErrorText("Enter a 6 character password.")));
    }

    /*
     * Test username already exists error
     */
    @Test
    public void test_createAccountUserExists_returnFail() {
        // Given
        onView(withId(R.id.et_username))
                .perform(typeText("espresso"), closeSoftKeyboard());
        onView(withId(R.id.et_password))
                .perform(typeText("123456"), closeSoftKeyboard());
        // When
        onView(withId(R.id.buttonCreateAccount)).perform(click());
        onView(withId(R.id.buttonCreateAccount)).perform(click());
        // Then
        onView(withId(R.id.et_username))
                .check(matches(hasErrorText("Username already exists.")));
    }
}