package com.example.dennispersaudinventoryapplication.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dennispersaudinventoryapplication.Models.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insertUser(User user);

    @Update
    void updateUser(User user);

    @Delete
    void deleteUser(User user);

    // Return list of all user objects in the database
    @Query("SELECT * FROM user_table")
    List<User> getAllUsers();

    // Return user specified by name
    @Query("SELECT * FROM user_table WHERE user_name = :name")
    User getUserByName(String name);

    @Query("SELECT user_password FROM user_table where user_name = :name")
    String getPasswordByName(String name);

    @Query("SELECT user_name FROM user_table where user_name = :name")
    String getUsernameByName(String name);

//    @Query("SELECT * FROM user_table WHERE user_name = :username and user_password = :password")
//    LiveData<User> getUser(String username, String password);
}