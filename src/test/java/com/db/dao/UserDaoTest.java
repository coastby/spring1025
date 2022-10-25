package com.db.dao;

import com.db.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UserDaoFactory.class)
class UserDaoTest {
    @Autowired
    ApplicationContext context;
    private UserDao userDao;
    private User user1;
    private User user2;
    private User user3;

    @BeforeEach
    void setContext() throws SQLException {
        userDao = context.getBean("awsUserDao", UserDao.class);
        user1 = new User("1", "spring", "tobby");
        user2 = new User("2", "java", "spring");
        user3 = new User("3", "intellij", "idea");
        userDao.deleteAll();
    }
    @Test
    void addAndGetTest() throws SQLException {
        userDao.add(user1);
        userDao.add(user2);
        userDao.add(user3);

        User userT = userDao.findById(user1.getId());
        assertEquals(user1.getName(), userT.getName());
        assertEquals(user1.getPassword(), userT.getPassword());

        assertThrows(EmptyResultDataAccessException.class, () -> {
            userDao.findById("4");
        });
    }

    @Test
    void deleteAllTest() throws SQLException {
        userDao.add(user1);
        userDao.add(user2);
        userDao.add(user3);
        assertEquals(3, userDao.getCount());

        userDao.deleteAll();
        assertEquals(0, userDao.getCount());

    }

    @Test
    void findAllTest() throws SQLException {
        userDao.add(user1);
        userDao.add(user2);
        userDao.add(user3);

        List<User> result = userDao.findAll();
        assertEquals(3, result.size());

    }
}