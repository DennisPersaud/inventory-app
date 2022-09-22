package com.example.dennispersaudinventoryapplication.feature_inventory.presentation.ui.fragment;

import androidx.fragment.app.testing.FragmentScenario;

import com.example.dennispersaudinventoryapplication.feature_inventory.presentation.settings.MessageFragment;

import org.junit.Rule;

public class MessageFragmentTest {

    @Rule
    public FragmentScenario<MessageFragment> scenario =
            FragmentScenario.launchInContainer(MessageFragment.class);

}
