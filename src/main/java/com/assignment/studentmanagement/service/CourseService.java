package com.assignment.studentmanagement.service;

import com.assignment.studentmanagement.exception.ResourceNotFoundException;
import com.assignment.studentmanagement.model.Course;
import com.assignment.studentmanagement.repository.CourseRepository;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    // CREATE
    public void createCourse(Course course) {

        if(course == null) {
            throw new IllegalArgumentException("Course data cannot be null");
        }

        if(course.getName() == null || course.getName().isBlank()) {
            throw new IllegalArgumentException("Course name cannot be empty");
        }

        try {
            courseRepository.save(course);
        } catch (DuplicateKeyException e) {
            throw new IllegalArgumentException("Course already exists");
        }
    }

    // READ ALL
    public List<Course> getAllCourses(int page, int size) {
        return courseRepository.findAll(page, size);
    }

    // READ ONE
    public Course getCourseById(Integer id) {

        Course course = courseRepository.findById(id);

        if(course == null) {
            throw new ResourceNotFoundException("Course not found with id " + id);
        }

        return course;
    }

    // UPDATE
    public void updateCourse(Integer id, Course course) {

        try {
            int rows = courseRepository.update(id, course);

            if(rows == 0) {
                throw new ResourceNotFoundException("Course not found with id " + id);
            }

        } catch (DuplicateKeyException e) {
            throw new IllegalArgumentException("Course already exists");
        }
    }

    // DELETE
    public void deleteCourse(Integer id) {

        int rows = courseRepository.delete(id);

        if(rows == 0) {
            throw new ResourceNotFoundException("Course not found with id " + id);
        }
    }
}
