package com.assignment.studentmanagement.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DashboardRepository {

    private final JdbcTemplate jdbcTemplate;

    public DashboardRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getTotalStudents(Integer adminId) {
        String sql = "SELECT COUNT(*) FROM students WHERE admin_id = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, adminId);
    }

    public int getEnrollmentYears(Integer adminId) {
        String sql = "SELECT COUNT(DISTINCT YEAR(enrollment_date)) FROM students WHERE admin_id = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, adminId);
    }

    public int getEnrolledThisYear(Integer adminId) {
        String sql = """
            SELECT COUNT(*) FROM students
            WHERE admin_id = ?
            AND YEAR(enrollment_date) = YEAR(CURDATE())
        """;

        return jdbcTemplate.queryForObject(sql, Integer.class, adminId);
    }

    public int getEnrolledThisMonth(Integer adminId) {
        String sql = """
            SELECT COUNT(*) FROM students
            WHERE admin_id = ?
            AND YEAR(enrollment_date) = YEAR(CURDATE())
            AND MONTH(enrollment_date) = MONTH(CURDATE())
        """;

        return jdbcTemplate.queryForObject(sql, Integer.class, adminId);
    }
}
