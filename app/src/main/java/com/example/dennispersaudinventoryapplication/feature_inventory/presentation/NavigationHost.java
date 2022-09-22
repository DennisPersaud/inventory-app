package com.example.dennispersaudinventoryapplication.feature_inventory.presentation;

import androidx.fragment.app.Fragment;

public interface NavigationHost {
    void navigateTo(Fragment fragment, boolean addToBackstack);
}
