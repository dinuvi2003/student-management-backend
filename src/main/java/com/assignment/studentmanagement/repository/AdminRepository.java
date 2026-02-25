package com.assignment.studentmanagement.repository;

import com.assignment.studentmanagement.model.Admin;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class AdminRepository {

    private final JdbcTemplate jdbcTemplate;

    public AdminRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Admin(
                rs.getInt("id"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("full_name"),
                rs.getString("email"),
                rs.getBoolean("is_active")
        );
    }

    public Admin findByUsername(String username) {
        String sql = "SELECT * FROM admins WHERE username = ? AND is_active = true";

        try {
            return jdbcTemplate.queryForObject(sql, this::mapRow, username);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


}
