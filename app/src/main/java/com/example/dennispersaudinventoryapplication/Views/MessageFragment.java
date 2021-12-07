package com.example.dennispersaudinventoryapplication.Views;

import static android.content.Context.MODE_PRIVATE;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.dennispersaudinventoryapplication.R;
import com.example.dennispersaudinventoryapplication.databinding.MessageFragmentBinding;
import com.google.android.material.snackbar.Snackbar;

public class MessageFragment extends Fragment {

    // Notification channel constant
    public static final String CHANNEL_1_ID = "lowinventory";
    // SMS permission constant
    private final int SMS_PERMISSION_CODE = 1;
    MessageFragmentBinding messageFragmentBinding;
    // Initialize variables
    private NotificationManagerCompat notificationManager;

    public MessageFragment() {
        super(R.layout.message_fragment);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requireActivity().setContentView(R.layout.message_fragment);
        messageFragmentBinding = MessageFragmentBinding.inflate(inflater, container, false);

        // Hide title bar
//        requireActivity().getActionBar().setDisplayShowTitleEnabled(false);

        // Save switch state in shared preferences
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("save", MODE_PRIVATE);
        messageFragmentBinding.switchNotifications.setChecked(sharedPreferences.getBoolean("value", true));

        // Reference to Notification manager
        notificationManager = NotificationManagerCompat.from(requireActivity());

        // Create notification channel
        createNotificationChannels();

        // Notification switch listener
        messageFragmentBinding.switchNotifications.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(requireActivity(),
                    Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                if (messageFragmentBinding.switchNotifications.isChecked()) {

                    /* TODO: Implement database listener to check if inventory is low:
                     * I was not able to create the conditional statement to check
                     * if the inventory for an item is low because I could not
                     * find a way to create a listener for the SQLite database.
                     */

                    // When switch checked
                    SharedPreferences.Editor editor = requireActivity().getSharedPreferences("save", MODE_PRIVATE).edit();
                    editor.putBoolean("value", true);
                    editor.apply();
                    messageFragmentBinding.switchNotifications.setChecked(true);

                    // Build notification
                    Notification notification = new NotificationCompat.Builder(requireActivity(), CHANNEL_1_ID)
                            .setSmallIcon(R.drawable.ic_alert)
                            .setContentText("Item inventory is low or out of stock.")
                            .setPriority(NotificationCompat.PRIORITY_HIGH)
                            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                            .build();

                    // Display notification
                    notificationManager.notify(1, notification);
                } else {

                    // When switch unchecked
                    SharedPreferences.Editor editor = requireActivity().getSharedPreferences("save", MODE_PRIVATE).edit();
                    editor.putBoolean("value", false);
                    editor.apply();
                    messageFragmentBinding.switchNotifications.setChecked(false);
                }
            } else {

                // Permission not granted
                requestSMSPermission();
            }
        });
        return messageFragmentBinding.getRoot();
    }

    // Create notification channel
    private void createNotificationChannels() {

        // Check if build version is Oreo or higher

        // Create notification channel object
        NotificationChannel lowinventory = new NotificationChannel(
                CHANNEL_1_ID,
                "lowinventory",
                NotificationManager.IMPORTANCE_HIGH
        );

        // Channel settings
        lowinventory.setDescription("Alert user when inventory low");

        // Reference notification manager and create channel
        NotificationManager manager = requireActivity().getSystemService(NotificationManager.class);
        manager.createNotificationChannel(lowinventory);
    }

    // Request user for SMS permission
    private void requestSMSPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), Manifest.permission.SEND_SMS)) {

            // Build Permission
            // If the user chooses Ok grant request
            // If user chooses cancel dismiss permission
            new AlertDialog.Builder(requireActivity())
                    .setTitle("SMS Permission")
                    .setMessage("Permission needed to send SMS notifications")
                    .setPositiveButton("Ok", (dialog, which) -> ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.SEND_SMS}, SMS_PERMISSION_CODE))
                    .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                    .create()
                    .show();
        } else {

            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.SEND_SMS}, SMS_PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == SMS_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Snackbar.make(messageFragmentBinding.messageActivity, "Permission Granted",
                        Snackbar.LENGTH_SHORT).show();
            } else {

                Snackbar.make(messageFragmentBinding.messageActivity, "Permission Denied",
                        Snackbar.LENGTH_SHORT).show();
            }
        }
    }
}
