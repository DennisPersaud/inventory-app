package com.example.dennispersaudinventoryapplication.feature_inventory.presentation.vm;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.example.dennispersaudinventoryapplication.feature_inventory.domain.model.User;
import com.example.dennispersaudinventoryapplication.feature_inventory.domain.repository.UserRepository;

import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MainActivityViewModel extends AndroidViewModel {

    private final UserRepository userRepo;

    @Inject
    public MainActivityViewModel(Application application, UserRepository userRepo) {
        super(application);
        this.userRepo = userRepo;
//        mRepo = new MainRepository(application);
    }

    public void insertUser(User user) { userRepo.insertUser(user);}

    public String getPasswordByName(String name) throws ExecutionException, InterruptedException { return userRepo.getPasswordByName(name); }

    public String getUsernameByName(String name) throws ExecutionException, InterruptedException { return userRepo.getUsernameByName(name); }

}
/*
    public void updateUser(User user) { userRepo.updateUser(user);}

    public void deleteUser(User user) { userRepo.deleteUser(user);}

    public List<User> getAllUsers() throws ExecutionException, InterruptedException { return userRepo.getAllUsers();}

    public User getUserByName(String name) throws ExecutionException, InterruptedException { return userRepo.getUserByName(name); }
*/

//    public LiveData<User> getUser(String username, String password) { return mRepo.getUser(username, password); }
//    public void getUser(String username, String password) { mRepo.getUser(username, password); }

//    public List<User> loadAllUsers() throws ExecutionException, InterruptedException {return mRepo.loadAllUsers(); }
