package com.example.dennispersaudinventoryapplication.Models;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.room.RoomWarnings;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.dennispersaudinventoryapplication.Database.MainDatabase;
import com.example.dennispersaudinventoryapplication.Database.UserDao;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


@RunWith(AndroidJUnit4.class)
public class UserDaoTests extends TestCase {

    private MainDatabase db;
    private UserDao dao;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void createDb() {
        this.db = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().getContext(),
                MainDatabase.class)
                .allowMainThreadQueries()
                .build();
        dao = db.userDao();
    }

    @After
    public void closeDb() {
        db.close();
    }

    @Test
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    public void test_insertUser_matchUsername() {

        // Insert user into database
        User user = new User("Dennis", "123");
        dao.insertUser(user);

        // Get users from database
        User insertedUser = dao.getUserByName("Dennis");

        // Assert username inserted matches db username
        assertThat(insertedUser.getUserName(), equalTo(user.getUserName()));
    }

    @Test
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    public void test_insertUser_matchPassword() {

        // Insert user into database
        User user = new User("Dennis", "123");
        dao.insertUser(user);

        // Get users from database
        User insertedUser = dao.getUserByName("Dennis");

        // Assert user password matches db password
        assertThat(insertedUser.getUserPassword(), equalTo(user.getUserPassword()));
    }

    @Test
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    public void test_getPasswordByName() {

        // Insert user into database
        User user = new User("Dennis", "123");
        dao.insertUser(user);

        // Get inserted user from database
        String userPass = dao.getPasswordByName("Dennis");

        // Assert user inserted matches db user
        assertThat(userPass, equalTo(user.getUserPassword()));
    }

    @Test
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    public void test_updateUser_ReturnTrue() {

        // Insert user into database
        User user = new User("Dennis", "123");
        dao.insertUser(user);

        // Get inserted user & update name
        User updateUser = dao.getUserByName("Dennis");
        updateUser.setUserName("Mark");
        dao.updateUser(updateUser);

        // Get users from database
        List<User> insertedUsers = dao.getAllUsers();

        // Assert user inserted matches db user
        assertThat(insertedUsers.get(0).getUserName(), equalTo(updateUser.getUserName()));
    }

    @Test
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    public void test_deleteUser_ReturnTrue() {

        // Insert user into database
        User user = new User("Dennis", "123");
        dao.insertUser(user);

        // Get & delete user from database
        User deleteUser = dao.getUserByName("Dennis");
        dao.deleteUser(deleteUser);

        // Get inserted user from database
        List<User> insertedUsers = dao.getAllUsers();

        // Assert user inserted matches db user
        assertThat(insertedUsers.isEmpty(), is(true));
    }
}
