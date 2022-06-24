package com.example.dennispersaudinventoryapplication.ui.fragment;

import androidx.fragment.app.testing.FragmentScenario;

import org.junit.Before;
import org.junit.Rule;

public class LoginFragmentTest {

    @Rule
    public FragmentScenario<LoginFragment> loginScenario =
            FragmentScenario.launchInContainer(LoginFragment.class);

    @Before
    public void setUp() {
        loginScenario = FragmentScenario.launchInContainer(LoginFragment.class);
    }
}
