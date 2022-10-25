package com.db.dao;

import com.db.domain.User;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {
    @Test
    void addAndGetTest() throws SQLException, ClassNotFoundException {
        UserDao userDao = new UserDao(new AwsConnectionMaker());
        userDao.add(new User("1", "hoon", "cat"));
        userDao.add(new User("2", "python", "dog"));

        User user1 = userDao.findById("1");
        assertEquals("hoon", user1.getName());
        assertEquals("cat", user1.getPassword());
    }

}