package com.db.dao;

import com.db.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UserDaoFactory.class)
class UserDaoTest {
    @Autowired
    ApplicationContext context;
    @Test
    void addAndGetTest() throws SQLException, ClassNotFoundException {
        //Factory 적용
        UserDao userDao = context.getBean("awsUserDao", UserDao.class);
        userDao.add(new User("1", "hoon", "cat"));
        userDao.add(new User("2", "python", "dog"));

        User user1 = userDao.findById("1");
        assertEquals("hoon", user1.getName());
        assertEquals("cat", user1.getPassword());
    }

    @Test
    void deleteAllTest() throws SQLException, ClassNotFoundException {
        UserDao userDao = context.getBean("awsUserDao", UserDao.class);
        userDao.add(new User("6", "hoon", "cat"));
        assertEquals(1, userDao.getCount());

        userDao.deleteAll();
        assertEquals(0, userDao.getCount());

    }

}