package com.db.dao;

import com.db.domain.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Map;

public class UserDao {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }



    public void add(final User user) throws SQLException {
        jdbcTemplate.update("insert into Users(id, name, password) values(?, ?, ?)",user.getId(),user.getName(), user.getPassword());
    }

    public User findById(String id) throws SQLException {
        String sql = "SELECT * FROM Users where id = ?;";
        RowMapper<User> rowMapper = new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User(rs.getString("id"), rs.getString("name"), rs.getString("password"));
                return user;
            }
        } ;
        return this.jdbcTemplate.queryForObject(sql, rowMapper, id);
    }
    public List<User> findAll() {
        String sql = "SELECT * FROM Users;";
        RowMapper<User> rowMapper = new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User(rs.getString("id"), rs.getString("name"), rs.getString("password"));
                return user;
            }
        };
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    public void deleteAll() throws SQLException{
        jdbcTemplate.update("DELETE FROM Users;");
    }

    public int getCount() throws SQLException{
        String sql = "SELECT COUNT(*) FROM Users;";
        return this.jdbcTemplate.queryForObject(sql, Integer.class);
    }
}
