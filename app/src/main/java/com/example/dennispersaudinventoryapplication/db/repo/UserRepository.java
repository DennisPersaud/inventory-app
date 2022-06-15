package com.example.dennispersaudinventoryapplication.db.repo;

import com.example.dennispersaudinventoryapplication.db.model.User;

import java.util.concurrent.ExecutionException;

public interface UserRepository {
    void insertUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
    String getPasswordByName(String name) throws ExecutionException, InterruptedException;
    String getUsernameByName(String name) throws ExecutionException, InterruptedException;
}
