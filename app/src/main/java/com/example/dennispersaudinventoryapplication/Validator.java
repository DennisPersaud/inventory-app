package com.example.dennispersaudinventoryapplication;

public class Validator {

    public static final String REQUIRED = "REQUIRED";
    public static final String MIN_LENGTH = "MIN_LENGTH";

    // TODO: Crate unit test to test validate function
    public static boolean validate(String value, String flag) {
        if (flag.equals(REQUIRED)) {
            return value.length() != 0;
        }
        if (flag.equals(MIN_LENGTH)) {
            return value.length() < 6;
        }
        return true;
    }

//    public static boolean validateUserInfo(TextView username, TextView password){
//
//        String usernameInput = username.getText().toString();
//        String passwordInput = password.getText().toString();
//
//        if(!usernameInput.isEmpty() && !passwordInput.isEmpty()){
//
//            return true;
//        }else{
//
////            Toast.makeText(MainActivity.this, R.string.toast_invalidInput, Toast.LENGTH_SHORT).show();
//            Snackbar.make(mainActivity,
//                    R.string.toast_invalidInput, Snackbar.LENGTH_SHORT).show();
//            return false;
//        }
//    }
//
//    public static void verifyCredentials(TextView username, TextView password){
//
//        // Check if password entered matches password for username in database
//        UserData userData = new UserData(-1, username.getText().toString(), password.getText().toString());
//        DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
//
//        boolean success = databaseHelper.checkUsernamePassword(userData);
//
//        if(success){
//
//            // If password matches display login message and start DataActivty
//            Toast.makeText(MainActivity.this, R.string.toast_loginSuccess, Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(MainActivity.this, DataActivity.class);
//            startActivity(intent);
//        }else{
//
//            // If password does not match display message, decrement counter and disable button after 5 tries
//            Toast.makeText(MainActivity.this, R.string.toast_loginFailed, Toast.LENGTH_SHORT).show();
//            counter--;
//
//            if(counter == 0){
//
//                buttonLogin.setEnabled(false);
//            }
//        }
//    }
//
//    public static void verifyUser(TextView username, TextView password){
//
//        // Print error if user exists in system
//        userData = new UserData(-1, username.getText().toString(), password.getText().toString());
//        databaseHelper = new DatabaseHelper(MainActivity.this);
//
//
//        if(databaseHelper.checkUsername(userData)){
//
//            Toast.makeText(MainActivity.this, R.string.toast_accountExists, Toast.LENGTH_SHORT).show();
//        }else{
//
//            // Create new account and print message to user
//            boolean success = databaseHelper.addUser(userData);
//
//            if(success){
//
//                Toast.makeText(MainActivity.this, R.string.toast_createAccountSuccess, Toast.LENGTH_SHORT).show();
//            }else{
//
//                Toast.makeText(MainActivity.this, R.string.toast_createAccountFailed, Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
}
