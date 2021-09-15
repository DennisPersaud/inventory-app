package com.example.dennispersaudinventoryapplication.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dennispersaudinventoryapplication.Models.User;
import com.example.dennispersaudinventoryapplication.R;
import com.example.dennispersaudinventoryapplication.ViewModel.MainActivityViewModel;
import com.example.dennispersaudinventoryapplication.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    // Initialize variables
    ActivityMainBinding activityMainBinding;
    private MainActivityViewModel mainViewModel;
    private final int counter = 5;
    User userData;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);

        // Bind mainActivity
        // Initialize view model provider
        mainViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        // Login button listener
        activityMainBinding.buttonLogin.setOnClickListener(v -> {
            try {

                if (getPasswordInput().equals(mainViewModel.getPasswordByName(getUsernameInput()))) {
                    Snackbar.make(activityMainBinding.mainActivity, R.string.toast_loginSuccess,
                            Snackbar.LENGTH_SHORT).show();
                    // TODO: Instantiate new inventory for new users
                    intent = new Intent(MainActivity.this, DataActivity.class);
                    startActivity(intent);
                } else {

                    Snackbar.make(activityMainBinding.mainActivity, R.string.toast_loginFailed,
                            Snackbar.LENGTH_SHORT).show();
                }
            } catch (Exception e) {

                Snackbar.make(activityMainBinding.mainActivity, Objects.requireNonNull(e.getMessage()),
                        Snackbar.LENGTH_SHORT).show();
            }
        });

        // Create account button listener
        activityMainBinding.buttonCreateAccount.setOnClickListener(v -> {
            try {
                if (!getUsernameInput().equals(mainViewModel.getUsernameByName(getUsernameInput()))) {
                    if (!getUsernameInput().isEmpty() && !getPasswordInput().isEmpty()) {
                        if (getPasswordInput().length() > 5) {

                            userData = new User(getUsernameInput(), getPasswordInput());
                            mainViewModel.insertUser(userData);
                            Snackbar.make(activityMainBinding.mainActivity, R.string.toast_createAccountSuccess,
                                    Snackbar.LENGTH_SHORT).show();

                        } else {
                            activityMainBinding.etPassword.setError("Enter a 6 character password.");
                        }
                    } else {
                        activityMainBinding.etUsername.setError("Enter a username.");
                        activityMainBinding.etPassword.setError("Enter a 6 character password.");
                    }
                } else {
                    activityMainBinding.etUsername.setError("Username already exists.");
                }
            } catch (Exception e) {

                Snackbar.make(activityMainBinding.mainActivity, Objects.requireNonNull(e.getMessage()),
                        Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    private String getUsernameInput() {
        return Objects.requireNonNull(activityMainBinding.etUsername.getText()).toString();
    }

    private String getPasswordInput() {
        return Objects.requireNonNull(activityMainBinding.etPassword.getText()).toString();
    }
}