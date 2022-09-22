package com.example.dennispersaudinventoryapplication.feature_inventory.alerts;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;

import androidx.core.app.ActivityCompat;

public class RequestSMS {

    private static final int SMS_PERMISSION_CODE = 1;

    public static void requestSMSPermission(Activity activity) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.SEND_SMS)) {

            // Build Permission
            // If the user chooses Ok grant request
            // If user chooses cancel dismiss permission
            new AlertDialog.Builder(activity)
                    .setTitle("SMS Permission")
                    .setMessage("Permission needed to send SMS notifications")
                    .setPositiveButton("Ok", (dialog, which) -> ActivityCompat.requestPermissions(activity,
                            getPermission(), SMS_PERMISSION_CODE))
                    .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                    .create()
                    .show();
        } else {
            ActivityCompat.requestPermissions(activity, getPermission(), SMS_PERMISSION_CODE);
        }
    }

    public static String[] getPermission() {
        return new String[]{Manifest.permission.SEND_SMS};
    }
}