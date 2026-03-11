package com.assignment.studentmanagement.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DashboardRepository {

    private final JdbcTemplate jdbcTemplate;

    public DashboardRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getTotalStudents() {
        String sql = "SELECT COUNT(*) FROM students";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public int getEnrollmentYears() {
        String sql = "SELECT COUNT(DISTINCT YEAR(enrollment_date)) FROM students";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public int getEnrolledThisYear() {
        String sql = """
            SELECT COUNT(*) FROM students
            WHERE YEAR(enrollment_date) = YEAR(CURDATE())
        """;

        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public int getEnrolledThisMonth() {
        String sql = """
            SELECT COUNT(*) FROM students
            WHERE YEAR(enrollment_date) = YEAR(CURDATE())
            AND MONTH(enrollment_date) = MONTH(CURDATE())
        """;

        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public int getTotalCourses() {
        String sql = "SELECT COUNT(*) FROM courses";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public String getMostEnrolledCourse() {
        String sql = """
            SELECT c.name
            FROM courses c
            JOIN student_courses sc ON c.id = sc.course_id
            GROUP BY c.id, c.name
            ORDER BY COUNT(sc.student_id) DESC
            LIMIT 1
        """;
        return jdbcTemplate.queryForObject(sql, String.class);
    }
}
