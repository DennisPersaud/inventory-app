package com.example.dennispersaudinventoryapplication.feature_inventory.alerts;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;

public class NotificationChannels {

    public static final String CHANNEL_1_ID = "lowInventory";

    public static void createNotificationChannels(Activity activity) {

        // Check if build version is Oreo or higher

        // Create notification channel object
        NotificationChannel lowInventory = getNotificationChannel();

        // Channel settings
        lowInventory.setDescription("Alert user when inventory low");

        // Reference notification manager and create channel
        NotificationManager manager = activity.getSystemService(NotificationManager.class);
        manager.createNotificationChannel(lowInventory);
    }

    private static NotificationChannel getNotificationChannel() {
        return new NotificationChannel(CHANNEL_1_ID, "lowInventory", NotificationManager.IMPORTANCE_HIGH);

    }
}