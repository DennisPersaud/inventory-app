package com.example.dennispersaudinventoryapplication.respoitories;

import com.example.dennispersaudinventoryapplication.feature_inventory.domain.model.User;
import com.example.dennispersaudinventoryapplication.feature_inventory.domain.repository.UserRepository;

import java.util.ArrayList;

public class FakeUserRepository implements UserRepository {

    private final ArrayList<User> userList = new ArrayList<>();


    @Override
    public void insertUser(User user) {
        userList.add(user);
    }

    @Override
    public void updateUser(User user) {
        userList.set(0, user);
    }

    @Override
    public void deleteUser(User user) {
        userList.remove(user);
    }

    @Override
    public String getPasswordByName(String name) {
        User result = null;
        for (User user : userList) {
            if (user.getUserName().equals(name)) {
                result = user;
                break;
            }
        }
        assert result != null;
        return result.getUserPassword();
    }

    @Override
    public String getUsernameByName(String name) {
        User result = null;
        for (User user : userList) {
            if (user.getUserName().equals(name)) {
                result = user;
                break;
            }
        }
        assert result != null;
        return result.getUserName();
    }
}
