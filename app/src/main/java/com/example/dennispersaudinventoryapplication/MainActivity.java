package com.example.dennispersaudinventoryapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dennispersaudinventoryapplication.Utils.Validator;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    // Initialize variables
    private TextView username, password;
    private Button buttonLogin, buttonCreateAccount;
    private View mainActivity;
    private int counter = 5;
    //    DatabaseHelper databaseHelper;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        databaseHelper = new DatabaseHelper(this);

        // Initialize views
        initViews();

        // Login button listener
        buttonLogin.setOnClickListener(v -> {
            try {
                if (Validator.validateUserInput(getUsernameString(), getPasswordString(), "REQUIRED") &&
                        Validator.validateUserInput(getUsernameString(), getPasswordString(), "MAX_LENGTH")) {
                    if (Validator.validateCredentials(getUsernameString(), getPasswordString(), MainActivity.this)) {

                        // If password matches display login message and start DataActivity
                        Toast.makeText(MainActivity.this, R.string.toast_loginSuccess, Toast.LENGTH_SHORT).show();
                        Snackbar.make(mainActivity,
                                R.string.toast_loginSuccess, Snackbar.LENGTH_SHORT).show();
                        intent = new Intent(MainActivity.this, DataActivity.class);
                        startActivity(intent);
                    } else {

                        // If password does not match display message,
                        // decrement counter and disable button after 5 tries
                        Toast.makeText(MainActivity.this, R.string.toast_loginFailed, Toast.LENGTH_SHORT).show();
                        Snackbar.make(mainActivity,
                                R.string.toast_loginFailed, Snackbar.LENGTH_SHORT).show();
                        decrementCounter();
                    }
                }
            } catch (Exception e) {

                Snackbar.make(mainActivity,
                        Objects.requireNonNull(e.getMessage()), Snackbar.LENGTH_SHORT).show();
            }
        });

        // Create account button listener
        buttonCreateAccount.setOnClickListener(v -> {
            try {
                if (Validator.validateUserInput(getUsernameString(), getPasswordString(), "REQUIRED") &&
                        Validator.validateUserInput(getUsernameString(), getPasswordString(), "MAX_LENGTH")) {
                    if (Validator.validateUser(getUsernameString(), getPasswordString(), MainActivity.this)) {

                        // If user exists in system, notify user
                        Toast.makeText(MainActivity.this, R.string.toast_accountExists, Toast.LENGTH_SHORT).show();
                        Snackbar.make(mainActivity,
                                R.string.toast_accountExists, Snackbar.LENGTH_SHORT).show();
                    } else {

                        // Create new account, notify user on success or failure
                        if (Validator.validateAddUser(getUsernameString(), getPasswordString(), MainActivity.this)) {

                            Toast.makeText(MainActivity.this, R.string.toast_createAccountSuccess, Toast.LENGTH_SHORT).show();
                            Snackbar.make(mainActivity,
                                    R.string.toast_createAccountSuccess, Snackbar.LENGTH_SHORT).show();
                        } else {

                            Toast.makeText(MainActivity.this, R.string.toast_createAccountFailed, Toast.LENGTH_SHORT).show();
                            Snackbar.make(mainActivity,
                                    R.string.toast_createAccountFailed, Snackbar.LENGTH_SHORT).show();
                        }
                    }
                }
            } catch (Exception e) {

                Snackbar.make(mainActivity,
                        Objects.requireNonNull(e.getMessage()), Snackbar.LENGTH_SHORT).show();
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
    }

    private String getUsernameString() {
        return username.getText().toString();
    }

    private String getPasswordString() {
        return password.getText().toString();
    }

    private void decrementCounter() {
        counter--;

        if (counter == 0) {

            buttonLogin.setEnabled(false);
        }
    }
}