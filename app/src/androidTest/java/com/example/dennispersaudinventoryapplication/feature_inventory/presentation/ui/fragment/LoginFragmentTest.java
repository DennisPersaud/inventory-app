package com.example.dennispersaudinventoryapplication.feature_inventory.presentation.ui.fragment;

import androidx.fragment.app.testing.FragmentScenario;

import com.example.dennispersaudinventoryapplication.feature_inventory.presentation.login.LoginFragment;

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
