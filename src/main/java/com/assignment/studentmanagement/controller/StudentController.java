package com.assignment.studentmanagement.controller;

import com.assignment.studentmanagement.dto.StudentCourseDTO;
import com.assignment.studentmanagement.model.Course;
import com.assignment.studentmanagement.model.Student;
import com.assignment.studentmanagement.dto.CreateStudentRequest;
import com.assignment.studentmanagement.service.StudentService;
import com.assignment.studentmanagement.security.CustomUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public void createStudent(@RequestBody CreateStudentRequest request, Authentication authentication) {
        Integer adminId = getAdminId(authentication);
        studentService.createStudent(
                request.getStudent(),
                adminId,
                request.getCourseIds(),
                request.getEnrollmentDates()
        );
    }

    @GetMapping
    public List<Student> getAllStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return studentService.getAllStudents(page, size);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Student>> searchStudents(
            @RequestParam String keyword,
            @RequestParam int page,
            @RequestParam int size
    ) {
        List<Student> students = studentService.searchStudents(keyword, page, size);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Integer id) {
        return studentService.getStudentById(id);
    }

    @PutMapping("/{id}")
    public void updateStudent(@PathVariable Integer id, @RequestBody Student student) {
        studentService.updateStudent(id, student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
    }

    @GetMapping("/{id}/courses")
    public List<StudentCourseDTO> getCoursesOfStudent(@PathVariable Integer id) {

        return studentService.getCoursesByStudent(id);
    }

    private Integer getAdminId(Authentication authentication) {
       CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
       return userDetails.getAdminId();
    }
}
