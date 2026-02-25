package com.assignment.studentmanagement.controller;

import com.assignment.studentmanagement.model.Student;
import com.assignment.studentmanagement.service.StudentService;
import com.assignment.studentmanagement.security.CustomUserDetails;
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
    public void createStudent(@RequestBody Student student, Authentication authentication) {
        Integer adminId = getAdminId(authentication);
        studentService.createStudent(student, adminId);
    }

    @GetMapping
    public List<Student> getAllStudents(Authentication authentication) {
        Integer adminId = getAdminId(authentication);
        return studentService.getAllStudents(adminId);
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Integer id, Authentication authentication) {
        Integer adminId = getAdminId(authentication);
        return studentService.getStudentById(id, adminId);
    }

    @PutMapping("/{id}")
    public void updateStudent(@PathVariable Integer id, @RequestBody Student student, Authentication authentication) {
        Integer adminId = getAdminId(authentication);
        studentService.updateStudent(id, student, adminId);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Integer id, Authentication authentication) {
        Integer adminId = getAdminId(authentication);
        studentService.deleteStudent(id, adminId);
    }

    private Integer getAdminId(Authentication authentication) {
       CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
       return userDetails.getAdminId();
    }
}
