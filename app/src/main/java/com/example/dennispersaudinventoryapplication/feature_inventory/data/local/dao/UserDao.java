package com.example.dennispersaudinventoryapplication.feature_inventory.data.local.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dennispersaudinventoryapplication.feature_inventory.domain.model.User;

import java.util.List;
import java.util.concurrent.ExecutionException;

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
    List<User> getAllUsers() throws ExecutionException, InterruptedException;

    // Return user specified by name
    @Query("SELECT * FROM user_table WHERE user_name = :name")
    User getUserByName(String name) throws ExecutionException, InterruptedException;

    @Query("SELECT user_password FROM user_table where user_name = :name")
    String getPasswordByName(String name) throws ExecutionException, InterruptedException;

    @Query("SELECT user_name FROM user_table where user_name = :name")
    String getUsernameByName(String name) throws ExecutionException, InterruptedException;

//    @Query("SELECT * FROM user_table WHERE user_name = :username and user_password = :password")
//    LiveData<User> getUser(String username, String password);
}