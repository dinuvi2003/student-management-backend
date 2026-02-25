package com.assignment.studentmanagement.repository;

import com.assignment.studentmanagement.model.Student;
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
    public void save(Student student) {
        String sql = "INSERT INTO students (first_name, last_name, email, date_of_birth, enrollment_date, admin_id) VALUES (?,?,?,?,?,?)";

        jdbcTemplate.update(sql,
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getDateOfBirth(),
                student.getEnrollmentDate(),
                student.getAdminId()
        );
    }

    //READ ALL
    public List<Student> findAllByAdminId(Integer adminId) {
        String sql = "SELECT * FROM students WHERE admin_id = ?";

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Student(
                    rs.getInt("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("email"),
                    rs.getDate("date_of_birth").toLocalDate(),
                    rs.getDate("enrollment_date").toLocalDate(),
                    rs.getInt("admin_id")
                ), adminId);
    }

    //READ ONE
    public Student findByIdAndAdminId(Integer id, Integer adminId) {
        String sql = "SELECT * FROM students WHERE id = ? AND admin_id = ?";

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
                new Student(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getDate("date_of_birth").toLocalDate(),
                        rs.getDate("enrollment_date").toLocalDate(),
                        rs.getInt("admin_id")
                ), id, adminId);
    }

    //UPDATE
    public int update(Integer id, Student student, Integer adminId) {
        String sql = """
            UPDATE students 
            SET first_name = ?, 
                last_name = ?, 
                email = ?, 
                date_of_birth = ?, 
                enrollment_date = ?
            WHERE id = ? AND admin_id = ?
        """;

        return jdbcTemplate.update(sql,
               student.getFirstName(),
               student.getLastName(),
               student.getEmail(),
               student.getDateOfBirth(),
               student.getEnrollmentDate(),
               id,
               adminId
        );
    }

    //DELETE
    public int delete(Integer id, Integer adminId) {
        String sql = "DELETE FROM students WHERE id = ? AND admin_id = ?";
        return jdbcTemplate.update(sql,id,adminId);
    }
}
