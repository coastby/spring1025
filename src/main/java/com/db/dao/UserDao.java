package com.db.dao;

import com.db.domain.User;

import java.sql.*;
import java.util.Map;

public class UserDao {
    private ConnectionMaker cm;
    private Connection c;

    public UserDao(ConnectionMaker cm) {
        this.cm = cm;
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        c = cm.getConnection();
        PreparedStatement ps = c.prepareStatement(
                "insert into Users(id, name, password) values(?, ?, ?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();

    }

    public User findById(String id) throws ClassNotFoundException, SQLException {
        c = cm.getConnection();

        PreparedStatement ps = c.prepareStatement(
                "select * from Users where id = ?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User(rs.getString("id"), rs.getString("name"), rs.getString("password"));

        rs.close();
        ps.close();
        c.close();

        return user;
    }
}
