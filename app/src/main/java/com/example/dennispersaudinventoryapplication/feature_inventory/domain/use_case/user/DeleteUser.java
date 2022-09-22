package com.example.dennispersaudinventoryapplication.feature_inventory.domain.use_case.user;

import com.example.dennispersaudinventoryapplication.feature_inventory.domain.model.User;
import com.example.dennispersaudinventoryapplication.feature_inventory.domain.repository.UserRepository;

public class DeleteUser {
    private final UserRepository repo;

    public DeleteUser(UserRepository repository) {
        this.repo = repository;
    }

    public void invoke(User user) {
        repo.deleteUser(user);
    }
}