package com.assignment.studentmanagement.repository;

import com.assignment.studentmanagement.model.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseRepository {

    private final JdbcTemplate jdbcTemplate;

    public CourseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Course> findAll(int page, int size) {

        int offset = page * size;

        String sql = """
                SELECT * FROM courses
                ORDER BY id
                LIMIT ? OFFSET ?
           """;

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Course(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description")
                ),
                size,
                offset
        );
    }

    public Course findById(Integer id) {

        String sql = "SELECT * FROM courses WHERE id = ?";

        return jdbcTemplate.queryForObject(sql,
                (rs, rowNum) -> new Course(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description")
                ),
                id
        );
    }

    public int save(Course course) {

        String sql = "INSERT INTO courses (name, description) VALUES (?, ?)";

        return jdbcTemplate.update(
                sql,
                course.getName(),
                course.getDescription()
        );
    }

    public int update(Integer id, Course course) {

        String sql = "UPDATE courses SET name=?, description=? WHERE id=?";

        return jdbcTemplate.update(
                sql,
                course.getName(),
                course.getDescription(),
                id
        );
    }

    public int delete(Integer id) {

        String sql = "DELETE FROM courses WHERE id=?";

        return jdbcTemplate.update(sql, id);
    }

}
