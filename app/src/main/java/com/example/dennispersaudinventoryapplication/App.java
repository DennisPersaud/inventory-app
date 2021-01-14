package com.example.dennispersaudinventoryapplication;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class App extends Application {

    // Notification channel constant
    public static final String CHANNEL_1_ID = "lowinventory";

    @Override
    public void onCreate(){
        super.onCreate();

        // Create notification channel
        createNotificationChannels();
    }

    // Create notification channel
    private void createNotificationChannels(){

        // Check if build version is Oreo or higher
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            // Create notification channel object
            NotificationChannel lowinventory = new NotificationChannel(
                    CHANNEL_1_ID,
                    "lowinventory",
                    NotificationManager.IMPORTANCE_HIGH
            );

            // Channel settings
            lowinventory.setDescription("Alert user when inventory low");

            // Reference notification manager and create channel
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(lowinventory);
        }
    }
}
