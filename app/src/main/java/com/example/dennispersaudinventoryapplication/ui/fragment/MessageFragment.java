package com.example.dennispersaudinventoryapplication.ui.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import com.example.dennispersaudinventoryapplication.R;
import com.example.dennispersaudinventoryapplication.alerts.NotificationBuilder;
import com.example.dennispersaudinventoryapplication.alerts.NotificationChannels;
import com.example.dennispersaudinventoryapplication.databinding.MessageFragmentBinding;
import com.example.dennispersaudinventoryapplication.utils.StandardMessages;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MessageFragment extends Fragment {

    // Notification channel constant
    MessageFragmentBinding messageFragmentBinding;

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

        // Create notification channel
        NotificationChannels.createNotificationChannels(requireActivity());

        // Notification switch listener
        messageFragmentBinding.switchNotifications.setOnClickListener(v -> NotificationBuilder.buildNotification
                (messageFragmentBinding, requireActivity(), NotificationManagerCompat.from(requireActivity())));
        return messageFragmentBinding.getRoot();
    }

    //TODO: Replace deprecated API
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        int SMS_PERMISSION_CODE = 1;
        if (requestCode == SMS_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                StandardMessages.displayToast(messageFragmentBinding.messageActivity ,"Permission Granted");
            } else {
                StandardMessages.displayToast(messageFragmentBinding.messageActivity, "Permission Denied");
            }
        }
    }
}