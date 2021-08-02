package com.example.dennispersaudinventoryapplication.Models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "user_table")
public class User {

    @ColumnInfo(name = "user_id")
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "user_name")
    private String userName;

    @ColumnInfo(name = "user_password")
    private String userPassword;

    // Constructors
    public User(String userName, String userPassword) {
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}