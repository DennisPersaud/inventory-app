package com.example.dennispersaudinventoryapplication;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidatorTest {

    /*
     * Test username input validation
     */
    @Test
    public void whenInputIsValid() {
        // Given
        String username = "testUsername";
        String flag = "REQUIRED";

        // When
        boolean result = Validator.validate(username, flag);

        // Then
        assertTrue(result);
    }
}