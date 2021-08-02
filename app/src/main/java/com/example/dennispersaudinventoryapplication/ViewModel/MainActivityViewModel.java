package com.example.dennispersaudinventoryapplication.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.dennispersaudinventoryapplication.Database.MainRepository;
import com.example.dennispersaudinventoryapplication.Models.User;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivityViewModel extends AndroidViewModel {

    public final MainRepository mRepo;

    public MainActivityViewModel(Application application) {
        super(application);
        mRepo = new MainRepository(application);
    }

    public void insertUser(User user) { mRepo.insertUser(user);}

    public void updateUser(User user) { mRepo.updateUser(user);}

    public void deleteUser(User user) { mRepo.deleteUser(user);}

    public List<User> getAllUsers() throws ExecutionException, InterruptedException { return mRepo.getAllUsers();}

    public User getUserByName(String name) throws ExecutionException, InterruptedException { return mRepo.getUserByName(name); }

    public String getPasswordByName(String name) throws ExecutionException, InterruptedException { return mRepo.getPasswordByName(name); }

    public String getUsernameByName(String name) throws ExecutionException, InterruptedException { return mRepo.getUsernameByName(name); }


//    public LiveData<User> getUser(String username, String password) { return mRepo.getUser(username, password); }
//    public void getUser(String username, String password) { mRepo.getUser(username, password); }

//    public List<User> loadAllUsers() throws ExecutionException, InterruptedException {return mRepo.loadAllUsers(); }
}