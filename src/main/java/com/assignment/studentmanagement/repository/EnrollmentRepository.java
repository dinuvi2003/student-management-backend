/*package com.assignment.studentmanagement.repository;

import com.assignment.studentmanagement.model.Course;
import com.assignment.studentmanagement.model.Student;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class EnrollmentRepository {

    private final JdbcTemplate jdbcTemplate;

    public EnrollmentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void enrollStudent(int studentId, int courseId, LocalDate enrollmentDate) {

        String sql = """
            INSERT INTO student_courses (student_id, course_id, enrollment_date)
            VALUES (?, ?, ?)
        """;

        jdbcTemplate.update(sql, studentId, courseId, enrollmentDate);
    }

    public List<Course> getCoursesByStudentId(Integer studentId) {

        String sql = """
        SELECT c.id, c.name, c.description
        FROM courses c
        JOIN student_courses sc ON c.id = sc.course_id
        WHERE sc.student_id = ?
    """;

        return jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(Course.class),
                studentId);
    }

    public List<Student> getStudentsByCourseId(Integer courseId) {

        String sql = """
            SELECT s.id,
                   s.first_name AS firstName,
                   s.last_name AS lastName,
                   s.email,
                   s.date_of_birth AS dateOfBirth,
                   s.enrollment_date AS enrollmentDate,
                   s.admin_id AS adminId
            FROM students s
            JOIN student_courses sc ON s.id = sc.student_id
            WHERE sc.course_id = ?
        """;

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Student.class), courseId);
    }

    public int deleteEnrollment(Integer studentId, Integer courseId){

        String sql = """
        DELETE FROM student_courses
        WHERE student_id = ? AND course_id = ?
    """;

        return jdbcTemplate.update(sql, studentId, courseId);
    }

}*/

package com.assignment.studentmanagement.repository;

import com.assignment.studentmanagement.dto.CourseStudentDTO;
import com.assignment.studentmanagement.dto.StudentCourseDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class EnrollmentRepository {

    private final JdbcTemplate jdbcTemplate;

    public EnrollmentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Enroll a student in a course with enrollment date
    public void enrollStudent(int studentId, int courseId, LocalDate enrollmentDate) {
        String sql = """
            INSERT INTO student_courses (student_id, course_id, enrollment_date)
            VALUES (?, ?, ?)
        """;
        jdbcTemplate.update(sql, studentId, courseId, enrollmentDate);
    }

    // Get courses of a student including enrollment date
    public List<StudentCourseDTO> getCoursesByStudentId(Integer studentId) {
        String sql = """
            SELECT c.id AS courseId,
                   c.name AS courseName,
                   c.description AS courseDescription,
                   sc.enrollment_date AS enrollmentDate
            FROM courses c
            JOIN student_courses sc ON c.id = sc.course_id
            WHERE sc.student_id = ?
        """;

        return jdbcTemplate.query(sql,
                (rs, rowNum) -> new StudentCourseDTO(
                        rs.getInt("courseId"),
                        rs.getString("courseName"),
                        rs.getString("courseDescription"),
                        rs.getDate("enrollmentDate").toLocalDate()
                ),
                studentId);
    }

    // Get students in a course including enrollment date
    public List<CourseStudentDTO> getStudentsByCourseId(Integer courseId) {
        String sql = """
            SELECT s.id AS studentId,
                   s.first_name AS firstName,
                   s.last_name AS lastName,
                   sc.enrollment_date AS enrollmentDate
            FROM students s
            JOIN student_courses sc ON s.id = sc.student_id
            WHERE sc.course_id = ?
        """;

        return jdbcTemplate.query(sql,
                (rs, rowNum) -> new CourseStudentDTO(
                        rs.getInt("studentId"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getDate("enrollmentDate").toLocalDate()
                ),
                courseId);
    }

    // Remove student enrollment from a course
    public int deleteEnrollment(Integer studentId, Integer courseId) {
        String sql = """
            DELETE FROM student_courses
            WHERE student_id = ? AND course_id = ?
        """;
        return jdbcTemplate.update(sql, studentId, courseId);
    }
}
