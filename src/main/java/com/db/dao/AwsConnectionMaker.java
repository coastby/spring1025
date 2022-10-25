package com.db.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class AwsConnectionMaker implements ConnectionMaker{
    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Map<String, String> env = System.getenv();
        String dbUrl = env.get("DB_URL");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection c = DriverManager.getConnection(
                dbUrl, dbUser, dbPassword);
        return c;
    }
}