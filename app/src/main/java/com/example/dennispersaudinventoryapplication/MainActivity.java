package com.example.dennispersaudinventoryapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    // Initialize variables
    private TextView username, password;
    private Button buttonLogin, buttonCreateAccount;
    private View mainActivity;
    private int counter = 5;
    Validator validator;
    DatabaseHelper databaseHelper;
    UserData userData;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        databaseHelper = new DatabaseHelper(this);
        initViews();

        // Login button listener
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                    if(validateUserInfo(username, password)){
                        verifyCredentials(username, password);
                    }else{
                        Toast.makeText(MainActivity.this, R.string.toast_loginFailed, Toast.LENGTH_SHORT).show();
//                        Snackbar.make(mainActivity,
//                                R.string.toast_loginFailed, Snackbar.LENGTH_SHORT).show();
                    }
                }catch (Exception  e){

                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                    Snackbar.make(mainActivity,
//                            Objects.requireNonNull(e.getMessage()), Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        // Create account button listener
        buttonCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if(validateUserInfo(username, password)){
                        verifyUser(username, password);
                    }else{
//                        Toast.makeText(MainActivity.this, R.string.toast_createAccountFailed, Toast.LENGTH_SHORT).show();
                        Snackbar.make(mainActivity,
                                R.string.toast_createAccountFailed, Snackbar.LENGTH_SHORT).show();
                    }
                }catch(Exception e){

//                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    Snackbar.make(mainActivity,
                            Objects.requireNonNull(e.getMessage()), Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validateUserInfo(TextView username, TextView password){

        String usernameInput = username.getText().toString();
        String passwordInput = password.getText().toString();

        if(!usernameInput.isEmpty() && !passwordInput.isEmpty()){

            return true;
        }else{

            Toast.makeText(MainActivity.this, R.string.toast_invalidInput, Toast.LENGTH_SHORT).show();
//            Snackbar.make(mainActivity,
//                    R.string.toast_invalidInput, Snackbar.LENGTH_SHORT).show();
            return false;
        }
    }

    private void verifyCredentials(TextView username, TextView password){

        // Check if password entered matches password for username in database
        userData = new UserData(-1, username.getText().toString(), password.getText().toString());
        databaseHelper = new DatabaseHelper(MainActivity.this);

        boolean success = databaseHelper.checkUsernamePassword(userData);

        if(success){

            // If password matches display login message and start DataActivty
            Toast.makeText(MainActivity.this, R.string.toast_loginSuccess, Toast.LENGTH_SHORT).show();
            intent = new Intent(MainActivity.this, DataActivity.class);
            startActivity(intent);
        }else{

            // If password does not match display message, decrement counter and disable button after 5 tries
            Toast.makeText(MainActivity.this, R.string.toast_loginFailed, Toast.LENGTH_SHORT).show();
            counter--;

            if(counter == 0){

                buttonLogin.setEnabled(false);
            }
        }
    }

    private void verifyUser(TextView username, TextView password){

        // Print error if user exists in system
        userData = new UserData(-1, username.getText().toString(), password.getText().toString());
        databaseHelper = new DatabaseHelper(MainActivity.this);

        if(databaseHelper.checkUsername(userData)){

            Toast.makeText(MainActivity.this, R.string.toast_accountExists, Toast.LENGTH_SHORT).show();
        }else{

            // Create new account and print message to user
            boolean success = databaseHelper.addUser(userData);

            if(success){

                Toast.makeText(MainActivity.this, R.string.toast_createAccountSuccess, Toast.LENGTH_SHORT).show();
            }else{

                Toast.makeText(MainActivity.this, R.string.toast_createAccountFailed, Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Initialize views
    private void initViews(){

        mainActivity = (View)findViewById(R.id.mainActivity);
        username = (TextView)findViewById(R.id.editTextUsername);
        password = (TextView)findViewById(R.id.editTextPassword);
        buttonLogin = (Button)findViewById(R.id.buttonLogin);
        buttonCreateAccount = (Button)findViewById(R.id.buttonCreateAccount);
    }
}