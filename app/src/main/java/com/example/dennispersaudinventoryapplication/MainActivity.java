package com.example.dennispersaudinventoryapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dennispersaudinventoryapplication.Models.User;
import com.example.dennispersaudinventoryapplication.ViewModel.MainActivityViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    // Initialize variables
    private TextView username, password;
    private Button buttonLogin, buttonCreateAccount;
    private View mainActivity;
    private MainActivityViewModel mainViewModel;
    private final int counter = 5;
    DatabaseHelper databaseHelper;
    User userData;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        initViews();

        // Login button listener
        buttonLogin.setOnClickListener(v -> {
            try {

                if (getPasswordInput().equals(mainViewModel.getPasswordByName(getUsernameInput()))) {
                    Snackbar.make(mainActivity, R.string.toast_loginSuccess, Snackbar.LENGTH_SHORT).show();
                    intent = new Intent(MainActivity.this, DataActivity.class);
                    startActivity(intent);
                } else {

                    Snackbar.make(mainActivity, R.string.toast_loginFailed, Snackbar.LENGTH_SHORT).show();
                }
            } catch (Exception e) {

                Snackbar.make(mainActivity, Objects.requireNonNull(e.getMessage()), Snackbar.LENGTH_SHORT).show();
            }
        });

        // Create account button listener
        buttonCreateAccount.setOnClickListener(v -> {
            try {
                if (!getUsernameInput().equals(mainViewModel.getUsernameByName(getUsernameInput()))) {
                    if (!getUsernameInput().isEmpty() && !getPasswordInput().isEmpty()) {
                        if (getPasswordInput().length() > 5) {

                            userData = new User(getUsernameInput(), getPasswordInput());
                            mainViewModel.insertUser(userData);
                            Snackbar.make(mainActivity, R.string.toast_createAccountSuccess, Snackbar.LENGTH_SHORT).show();

                        } else {
                            password.setError("Enter a 6 character password.");
                        }
                    } else {
                        username.setError("Enter a username.");
                        password.setError("Enter a 6 character password.");
                    }
                } else {
                    username.setError("Username already exists.");
                }
            } catch (Exception e) {

                Snackbar.make(mainActivity, Objects.requireNonNull(e.getMessage()), Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    // Initialize views
    private void initViews() {

        mainActivity = findViewById(R.id.mainActivity);
        username = findViewById(R.id.editTextUsername);
        password = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonCreateAccount = findViewById(R.id.buttonCreateAccount);

        databaseHelper = new DatabaseHelper(this);
        mainViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
    }

    private String getUsernameInput() {
        return username.getText().toString();
    }

    private String getPasswordInput() {
        return password.getText().toString();
    }
}