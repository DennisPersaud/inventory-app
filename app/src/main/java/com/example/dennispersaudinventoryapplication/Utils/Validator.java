package com.example.dennispersaudinventoryapplication.Utils;

import android.content.Context;

import com.example.dennispersaudinventoryapplication.DatabaseHelper;
import com.example.dennispersaudinventoryapplication.Models.User;

public class Validator {

    public static final String REQUIRED = "REQUIRED";
    public static final String MIN_LENGTH = "MIN_LENGTH";

    public static boolean validateUserInput(String username, String password, String flag) {

        if (flag.equals(REQUIRED)) {
            return !username.isEmpty() && !password.isEmpty();
        }
        if (flag.equals(MIN_LENGTH)) {
            return password.length() > 6;
        }

        return false;
    }

    public static boolean validateCredentials(String username, String password, Context context) {

        // Check if password entered matches password for username in database
        User userData = new User(username, password);
        DatabaseHelper databaseHelper = new DatabaseHelper(context);

        return databaseHelper.checkUsernamePassword(userData);
    }

    public static boolean validateUser(String username, String password, Context context) {

        User userData = new User(username, password);
        DatabaseHelper databaseHelper = new DatabaseHelper(context);

        // Check if user exists in system
        return databaseHelper.checkUsername((userData));
    }

    public static boolean validateAddUser(String username, String password, Context context) {
        User userData = new User(username, password);
        DatabaseHelper databaseHelper = new DatabaseHelper(context);

        return databaseHelper.addUser(userData);
    }
}
