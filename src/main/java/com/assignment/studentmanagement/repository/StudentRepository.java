package com.assignment.studentmanagement.repository;

import com.assignment.studentmanagement.model.Student;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository {

    private final JdbcTemplate jdbcTemplate;

    public StudentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //CREATE
    public Integer save(Student student) {
        String sql = "INSERT INTO students (first_name, last_name, email, date_of_birth, enrollment_date, admin_id) VALUES (?,?,?,?,?,?)";

        jdbcTemplate.update(sql,
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getDateOfBirth(),
                student.getEnrollmentDate(),
                student.getAdminId()
        );

        return jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
    }

    //READ ALL
    public List<Student> findAllByAdminId(int page, int size) {

        int offset = page * size;

        String sql = """
                    SELECT * FROM students
                    ORDER BY id DESC
                    LIMIT ? OFFSET ?
               """;

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Student(
                    rs.getInt("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("email"),
                    rs.getDate("date_of_birth").toLocalDate(),
                    rs.getDate("enrollment_date").toLocalDate(),
                    rs.getInt("admin_id")
                ),
                size,
                offset
        );
    }

    public List<Student> searchStudents(String keyword, int size, int offset) {

        String sql = """
        SELECT * FROM students
        WHERE (first_name LIKE ? OR last_name LIKE ? OR email LIKE ?)
        ORDER BY id DESC
        LIMIT ? OFFSET ?
    """;

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                        new Student(
                                rs.getInt("id"),
                                rs.getString("first_name"),
                                rs.getString("last_name"),
                                rs.getString("email"),
                                rs.getDate("date_of_birth").toLocalDate(),
                                rs.getDate("enrollment_date").toLocalDate(),
                                rs.getInt("admin_id")
                        ),

                "%" + keyword + "%",
                "%" + keyword + "%",
                "%" + keyword + "%",
                size,
                offset
        );
    }

    //READ ONE
    public Student findByIdAndAdminId(Integer id) {
        String sql = "SELECT * FROM students WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
                new Student(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getDate("date_of_birth").toLocalDate(),
                        rs.getDate("enrollment_date").toLocalDate(),
                        rs.getInt("admin_id")
                ), id);
    }

    //UPDATE
    public int update(Integer id, Student student) {
        String sql = """
            UPDATE students 
            SET first_name = ?, 
                last_name = ?, 
                email = ?, 
                date_of_birth = ?, 
                enrollment_date = ?
            WHERE id = ?
        """;

        return jdbcTemplate.update(sql,
               student.getFirstName(),
               student.getLastName(),
               student.getEmail(),
               student.getDateOfBirth(),
               student.getEnrollmentDate(),
               id
        );
    }

    //DELETE
    public int delete(Integer id) {
        String sql = "DELETE FROM students WHERE id = ?";
        return jdbcTemplate.update(sql,id);
    }


}
