package com.example.dennispersaudinventoryapplication.feature_inventory.domain.repository;

import com.example.dennispersaudinventoryapplication.feature_inventory.domain.model.User;

import java.util.concurrent.ExecutionException;

public interface UserRepository {
    void insertUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
    String getPasswordByName(String name) throws ExecutionException, InterruptedException;
    String getUsernameByName(String name) throws ExecutionException, InterruptedException;
}
