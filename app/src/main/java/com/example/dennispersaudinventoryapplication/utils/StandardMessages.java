package com.example.dennispersaudinventoryapplication.utils;

import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class StandardMessages {

    public static void displayToast(View v, CharSequence text) {
        Snackbar.make(v, text, Snackbar.LENGTH_SHORT).show();
    }

}
