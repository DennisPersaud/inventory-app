package com.example.dennispersaudinventoryapplication;

import android.content.Context;
import android.view.View;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@LargeTest
public class MainActivityTest {

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
     * Test if password input hint is displayed*/
    @Test
    public void test_visibility_passwordHint() {
        onView(withId(R.id.editTextPassword)).check(matches(withHint(R.string.hint_passwordInput)));
    }

    /*
     * Test login button with no credentials
     */
    @Ignore
    @Test
    public void loginButton_sameActivity() {
        onView(withId(R.id.buttonLogin)).perform(click());
        onView(withId(com.google.android.material.R.id.snackbar_text))
                .check(matches(isDisplayed()));
        onView(withId(com.google.android.material.R.id.snackbar_text))
                .check(matches(withText(R.string.toast_loginFailed)));
    }

    /*
     * Test create account button with no credentials
     */
    @Ignore
    @Test
    public void createAccountButton_sameActivity() {
        // Click button
        onView(withId(R.id.buttonCreateAccount)).perform(click());
        onView(withId(com.google.android.material.R.id.snackbar_text))
                .check(matches(isDisplayed()));
        // Check that the text was changed.
        onView(withId(com.google.android.material.R.id.snackbar_text))
                .check(matches(withText(R.string.toast_createAccountFailed)));
    }

    /*
     * Test login button with username only
     */
    @Ignore
    @Test
    public void loginUsername_newActivity() {
        // Type text and then press the button.
        onView(withId(R.id.editTextUsername))
                .perform(typeText(USERNAME_TO_BE_TYPED), closeSoftKeyboard());
        onView(withId(R.id.editTextPassword))
                .perform(typeText(PASSWORD_TO_BE_TYPED), closeSoftKeyboard());
        // Click button
        onView(withId(R.id.buttonLogin)).perform(click());
        // Check that the text was changed.
        onView(withId(R.id.editTextUsername))
                .check(matches(withText(USERNAME_TO_BE_TYPED)));
    }

    /*
     * Test login button with password only
     */
    @Ignore
    @Test
    public void loginPassword_newActivity() {
        // Type text and then press the button.
        onView(withId(R.id.editTextUsername))
                .perform(typeText(USERNAME_TO_BE_TYPED), closeSoftKeyboard());
        onView(withId(R.id.editTextPassword))
                .perform(typeText(PASSWORD_TO_BE_TYPED), closeSoftKeyboard());
        // Click button
        onView(withId(R.id.buttonLogin)).perform(click());
        // Check that the text was changed.
        onView(withId(R.id.editTextUsername))
                .check(matches(withText(USERNAME_TO_BE_TYPED)));
    }

    /*
     * Test login button with username & password
     */
    @Ignore
    @Test
    public void login_newActivity() {
        // Type text and then press the button.
        onView(withId(R.id.editTextUsername))
                .perform(typeText(USERNAME_TO_BE_TYPED), closeSoftKeyboard());
        onView(withId(R.id.editTextPassword))
                .perform(typeText(PASSWORD_TO_BE_TYPED), closeSoftKeyboard());
        // Click button
        onView(withId(R.id.buttonLogin)).perform(click());
        // Check that the text was changed.
        onView(withId(R.id.editTextUsername))
                .check(matches(withText(USERNAME_TO_BE_TYPED)));
    }

    /*
     * Test 3
     */
    @Ignore
    @Test
    public void createAccount_newActivity() {
        // Given
        onView(withId(R.id.editTextUsername))
                .perform(typeText(USERNAME_TO_BE_TYPED), closeSoftKeyboard());
        onView(withId(R.id.editTextPassword))
                .perform(typeText(PASSWORD_TO_BE_TYPED), closeSoftKeyboard());
        // When
        onView(withId(R.id.buttonLogin)).perform(click());
        // Then
        onView(withId(R.id.editTextUsername))
                .check(matches(withText(USERNAME_TO_BE_TYPED)));
    }
}