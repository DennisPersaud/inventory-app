package com.example.dennispersaudinventoryapplication.alerts;

import static android.content.Context.MODE_PRIVATE;

import android.Manifest;
import android.app.Activity;
import android.app.Notification;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import com.example.dennispersaudinventoryapplication.R;
import com.example.dennispersaudinventoryapplication.databinding.MessageFragmentBinding;

public class NotificationBuilder {

    public static final String CHANNEL_1_ID = "lowinventory";
    public static void buildNotification(MessageFragmentBinding binding, Activity activity, NotificationManagerCompat notificationManager) {

        if (ContextCompat.checkSelfPermission(activity,
                Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
            if (binding.switchNotifications.isChecked()) {

                /* TODO: Implement database listener to check if inventory is low:
                 * I was not able to create the conditional statement to check
                 * if the inventory for an item is low because I could not
                 * find a way to create a listener for the SQLite database.
                 */

                // When switch checked
                SharedPreferences.Editor editor = activity.getSharedPreferences("save", MODE_PRIVATE).edit();
                editor.putBoolean("value", true);
                editor.apply();
                binding.switchNotifications.setChecked(true);

                // Build notification
                Notification notification = getNotificationCompat(activity)
                        .setSmallIcon(R.drawable.ic_alert)
                        .setContentText("Item inventory is low or out of stock.")
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                        .build();

                // Display notification
                notificationManager.notify(1, notification);
            } else {

                // When switch unchecked
                SharedPreferences.Editor editor = activity.getSharedPreferences("save", MODE_PRIVATE).edit();
                editor.putBoolean("value", false);
                editor.apply();
                binding.switchNotifications.setChecked(false);
            }
        } else {

            // Permission not granted
            RequestSMS.requestSMSPermission(activity);
        }
    }

    private static NotificationCompat.Builder getNotificationCompat(Activity activity) {
        return new NotificationCompat.Builder(activity, CHANNEL_1_ID);
    }
}