package com.db.dao;

import com.db.domain.User;

import java.sql.*;
import java.util.Map;

public class UserDao {

    public void add() throws ClassNotFoundException, SQLException {
        ConnectionMaker cm = new AwsConnectionMaker();
        Connection c = cm.getConnection();
        PreparedStatement ps = c.prepareStatement(
                "insert into users(id, name, password) values(?, ?, ?)");
        ps.setString(1, "01");
        ps.setString(2, "Kyeongrok");
        ps.setString(3, "password");

        ps.executeUpdate();

        ps.close();
        c.close();

    }

    public User findById(String id) throws ClassNotFoundException, SQLException {
        ConnectionMaker cm = new AwsConnectionMaker();
        Connection c = cm.getConnection();

        PreparedStatement ps = c.prepareStatement(
                "select * from users where id = ?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User(rs.getString("id"), rs.getString("name"), rs.getString("password"));

        rs.close();
        ps.close();
        c.close();

        return user;
    }

    public static void main (String[]args) throws SQLException, ClassNotFoundException {
        UserDao userDao = new UserDao();
        userDao.add();
    }
}
