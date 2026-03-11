package com.assignment.studentmanagement.controller;

import com.assignment.studentmanagement.dto.CourseStudentDTO;
import com.assignment.studentmanagement.model.Course;
import com.assignment.studentmanagement.model.Student;
import com.assignment.studentmanagement.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> getAllCourses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        return courseService.getAllCourses(page,size);
    }

    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable Integer id) {
        return courseService.getCourseById(id);
    }

    @PostMapping
    public void createCourse(@RequestBody Course course) {
        courseService.createCourse(course);
    }

    @PutMapping("/{id}")
    public void updateCourse(@PathVariable Integer id,
                             @RequestBody Course course) {
        courseService.updateCourse(id, course);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Integer id) {
        courseService.deleteCourse(id);
    }

    @GetMapping("/{id}/students")
    public List<CourseStudentDTO> getStudentsInCourse(@PathVariable Integer id){

        return courseService.getStudentsByCourse(id);
    }

}
