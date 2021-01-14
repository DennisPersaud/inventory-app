package com.example.dennispersaudinventoryapplication;

import android.Manifest;
import android.app.Notification;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

public class MessageActivity extends AppCompatActivity {

    // SMS permission constant
    private int SMS_PERMISSION_CODE = 1;

    // Initialize variables
    private NotificationManagerCompat notificationManager;
    SwitchCompat switchCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        // Hide title bar
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Initialize view
        switchCompat = (SwitchCompat) findViewById(R.id.switchNotifications);

        // Save switch state in shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences("save", MODE_PRIVATE);
        switchCompat.setChecked(sharedPreferences.getBoolean("value", true));

        // Reference to Notification manager
        notificationManager = NotificationManagerCompat.from(this);

        // Notification switch listener
        switchCompat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(MessageActivity.this,
                        Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
                    if(switchCompat.isChecked()){

                        /* Implement database listener to check if inventory is low:
                         * I was not able to create the conditional statement to check
                         * if the inventory for an item is low because I could not
                         * find a way to create a listener for the SQLite database. */

                        // When switch checked
                        SharedPreferences.Editor editor = getSharedPreferences("save", MODE_PRIVATE).edit();
                        editor.putBoolean("value", true);
                        editor.apply();
                        switchCompat.setChecked(true);

                        // Build notification
                        Notification notification = new NotificationCompat.Builder(MessageActivity.this, App.CHANNEL_1_ID)
                                .setSmallIcon(R.drawable.ic_alert)
                                .setContentText("Item inventory is low or out of stock.")
                                .setPriority(NotificationCompat.PRIORITY_HIGH)
                                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                                .build();

                        // Display notification
                        notificationManager.notify(1, notification);
                    }else{

                        // When switch unchecked
                        SharedPreferences.Editor editor = getSharedPreferences("save", MODE_PRIVATE).edit();
                        editor.putBoolean("value", false);
                        editor.apply();
                        switchCompat.setChecked(false);
                    }
                }else{

                    // Permission not granted
                    requestSMSPermission();
                }
            }
        });
    }

    // Request user for SMS permission
    private void requestSMSPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)){

            // Build Permission
            new AlertDialog.Builder(this)
                    .setTitle("SMS Permission")
                    .setMessage("Permission needed to send SMS notifications")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                        // If the user chooses Ok grant request
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            ActivityCompat.requestPermissions(MessageActivity.this, new String[] {Manifest.permission.SEND_SMS}, SMS_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                        // If user chooses cancel dismiss permission
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create()
                    .show();
        }else{

            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.SEND_SMS}, SMS_PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == SMS_PERMISSION_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                Toast.makeText(this, "Permission GRANTED.", Toast.LENGTH_SHORT).show();
            }else{

                Toast.makeText(this, "Permission DENIED.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}