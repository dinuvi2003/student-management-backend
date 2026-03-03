package com.assignment.studentmanagement.service;

import com.assignment.studentmanagement.exception.ResourceNotFoundException;
import com.assignment.studentmanagement.model.Student;
import com.assignment.studentmanagement.repository.StudentRepository;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    //CREATE
    public void createStudent(Student student, Integer adminId) {

        if(student == null) {
            throw new IllegalArgumentException("Student data cannot be null");
        }

        Student newStudent = new Student(
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getDateOfBirth(),
                student.getEnrollmentDate(),
                adminId
        );

        try {
            studentRepository.save(newStudent);
        } catch (DuplicateKeyException e) {
            throw new IllegalArgumentException("Email already exists");
        }
    }

    //READ ALL
    public List<Student> getAllStudents(Integer adminId) {
        return studentRepository.findAllByAdminId(adminId);
    }

    //READ ONE
    public Student getStudentById(Integer id, Integer adminId) {

        try {
            return studentRepository.findByIdAndAdminId(id, adminId);
        }
        catch(EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Student not found or access denied");
        }
    }

    //UPDATE
    public void updateStudent(Integer id, Student student, Integer adminId) {
        try {
            int rows = studentRepository.update(id, student, adminId);

            if (rows == 0) {
                throw new ResourceNotFoundException("Student not found or access denied");
            }
        } catch (DuplicateKeyException e) {
            throw new IllegalArgumentException("Email already exists");
        }
    }

    //DELETE
    public void deleteStudent(Integer id, Integer adminId) {
        int rows = studentRepository.delete(id, adminId);

        if(rows == 0) {
            throw new ResourceNotFoundException("Student not found or access denied");
        }
    }
}

