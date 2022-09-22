package com.example.dennispersaudinventoryapplication.feature_inventory.presentation.ui.fragment;

import androidx.fragment.app.testing.FragmentScenario;

import com.example.dennispersaudinventoryapplication.feature_inventory.presentation.inventory.DataFragment;

import org.junit.Rule;

public class DataFragmentTest {

    @Rule
    public FragmentScenario<DataFragment> scenario =
            FragmentScenario.launchInContainer(DataFragment.class);

}
