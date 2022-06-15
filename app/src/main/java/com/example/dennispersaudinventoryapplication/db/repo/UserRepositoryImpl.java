package com.example.dennispersaudinventoryapplication.db.repo;

import com.example.dennispersaudinventoryapplication.db.AppDatabase;
import com.example.dennispersaudinventoryapplication.db.model.User;
import com.example.dennispersaudinventoryapplication.db.model.UserDao;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.inject.Inject;


public class UserRepositoryImpl implements UserRepository {
    private final UserDao userDao;

    @Inject
    public UserRepositoryImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    /*
     * Methods for user DAO
     */

    @Override
    public void insertUser(User user) {
        AppDatabase.getDatabaseWriterExecutor().execute(() -> userDao.insertUser(user));
    }

    @Override
    public void updateUser(User user) {
        AppDatabase.getDatabaseWriterExecutor().execute(() -> userDao.updateUser(user));
    }

    @Override
    public void deleteUser(User user) {
        AppDatabase.getDatabaseWriterExecutor().execute(() -> userDao.deleteUser(user));
    }

    @Override
    public String getPasswordByName(String name) throws ExecutionException, InterruptedException {
        Future<String> future = AppDatabase.getDatabaseWriterExecutor().submit(() -> userDao.getPasswordByName(name));
        return future.get();
    }

    @Override
    public String getUsernameByName(String name) throws ExecutionException, InterruptedException {
        Future<String> future = AppDatabase.getDatabaseWriterExecutor().submit(() -> userDao.getUsernameByName(name));
        return future.get();
    }

}
/*
    public List<User> getAllUsers() throws ExecutionException, InterruptedException {
        Future<List<User>> future = AppDatabase.getDatabaseWriterExecutor().submit(userDao::getAllUsers);
        return future.get();
    }

    public User getUserByName(String name) throws ExecutionException, InterruptedException {
        Future<User> future = AppDatabase.getDatabaseWriterExecutor().submit(() -> userDao.getUserByName(name));
        return future.get();
    }

*/


//    public LiveData<User> getUser(String username, String password) {
//        return userDao.getUser(username, password);
//    }

//    public List<User> loadAllUsers() throws ExecutionException, InterruptedException {
//        Future<List<User>> future = MainDatabase.getDatabaseWriterExecutor.submit(userDao::loadAllUsers);
//        return future.get();
//    }
