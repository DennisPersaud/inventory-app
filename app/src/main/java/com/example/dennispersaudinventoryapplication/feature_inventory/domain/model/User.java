package com.example.dennispersaudinventoryapplication.feature_inventory.domain.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.dennispersaudinventoryapplication.feature_inventory.domain.model.value_objects.Password;
import com.example.dennispersaudinventoryapplication.feature_inventory.domain.model.value_objects.Username;

@Entity(tableName = "user_table")
public class User {

    @ColumnInfo(name = "user_id")
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "user_name")
    private Username userName;

    @ColumnInfo(name = "user_password")
    private Password userPassword;

    // Constructors
    public User(Username userName, Password userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Username getUserName() {
        return userName;
    }

    public void setUserName(Username userName) {
        this.userName = userName;
    }

    public Password getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(Password userPassword) {
        this.userPassword = userPassword;
    }
}