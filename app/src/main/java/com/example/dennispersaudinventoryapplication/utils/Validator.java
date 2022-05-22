package com.example.dennispersaudinventoryapplication.utils;

public class Validator {
    public static boolean validateLogin(String password, String comparison) {
        return password.equals(comparison);
    }

    public static void validateRegistration() {

    }

    public static boolean validateNotEmpty(String username, String password) {
        return username.isEmpty() && password.isEmpty();
    }

    public static boolean validatePasswordLength(String password) {
        return password.length() >= 6;
    }

    public static boolean validateItemInfo(String itemName, String itemPrice, String itemCount) {
        return itemName.isEmpty() && itemPrice.isEmpty() && itemCount.isEmpty();
    }

    public static boolean validateUpdateItem(String itemPrice, String itemCount) {
        return itemPrice.isEmpty() && itemCount.isEmpty();
    }
}
