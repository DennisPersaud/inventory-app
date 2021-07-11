package com.example.dennispersaudinventoryapplication;

import com.example.dennispersaudinventoryapplication.Utils.Validator;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidatorTest {

    /*
     * Return true if input is not empty
     */
    @Test
    public void validate_stringNotEmpty_returnTrue() {
        // Given
        String username = "testUsername";
        String password = "testPassword";
        String flag = "REQUIRED";

        // When
        boolean result = Validator.validateUserInput(username, password, flag);

        // Then
        assertTrue(result);
    }

    /*
     * Return false if  input is empty
     */
    @Test
    public void validate_stringIsEmpty_returnFalse() {
        // Given
        String username = "";
        String password = "";
        String flag = "REQUIRED";

        // When
        boolean result = Validator.validateUserInput(username, password, flag);

        // Then
        assertFalse(result);
    }

    /*
     * Return true if input is greater than 6 characters
     */
    @Test
    public void validate_stringGreaterThanNumber_returnTrue() {
        String username = "testUsername";
        String password = "testPassword";
        String flag = "MIN_LENGTH";

        boolean result = Validator.validateUserInput(username, password, flag);
        assertTrue(result);
    }

    /*
     * Return false if input is less than 6 characters
     */
    @Test
    public void validate_stringLessThanNumber_returnFalse() {
        String username = "test";
        String password = "word";
        String flag = "MIN_LENGTH";

        boolean result = Validator.validateUserInput(username, password, flag);
        assertFalse(result);
    }

}