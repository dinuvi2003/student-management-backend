package com.assignment.studentmanagement.service;

import com.assignment.studentmanagement.dto.StudentCourseDTO;
import com.assignment.studentmanagement.exception.ResourceNotFoundException;
import com.assignment.studentmanagement.model.Course;
import com.assignment.studentmanagement.model.Student;
import com.assignment.studentmanagement.repository.EnrollmentRepository;
import com.assignment.studentmanagement.repository.StudentRepository;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final EnrollmentRepository enrollmentRepository;

    public StudentService(StudentRepository studentRepository, EnrollmentRepository enrollmentRepository) {
        this.studentRepository = studentRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    //CREATE
    public void createStudent(Student student, Integer adminId, List<Integer> courseIds, List<LocalDate> enrollmentDates) {

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

            // Save student
            Integer studentId = studentRepository.save(newStudent);

            // Enroll student to selected courses
            if (courseIds != null && !courseIds.isEmpty()) {

                for (int i = 0; i < courseIds.size(); i++) {
                    Integer courseId = courseIds.get(i);

                    // Get the enrollment date for this course, if provided
                    LocalDate enrollmentDate = (enrollmentDates != null && enrollmentDates.size() > i)
                            ? enrollmentDates.get(i)
                            : null;

                    enrollmentRepository.enrollStudent(studentId, courseId, enrollmentDate);
                }
            }

        } catch (DuplicateKeyException e) {
            throw new IllegalArgumentException("Email already exists");
        }
    }

    //READ ALL
    public List<Student> getAllStudents(int page, int size) {

        if(page < 0) {
            throw new IllegalArgumentException("Page number cannot be negative");
        }

        if(size <= 0) {
            throw new IllegalArgumentException("Page size must be greater than 0");
        }

        return studentRepository.findAllByAdminId(page, size);
    }

    //READ ONE
    public Student getStudentById(Integer id) {

        try {
            return studentRepository.findByIdAndAdminId(id);
        }
        catch(EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Student not found or access denied");
        }
    }

    //UPDATE
    public void updateStudent(Integer id, Student student) {
        try {
            int rows = studentRepository.update(id, student);

            if (rows == 0) {
                throw new ResourceNotFoundException("Student not found or access denied");
            }
        } catch (DuplicateKeyException e) {
            throw new IllegalArgumentException("Email already exists");
        }
    }

    //DELETE
    public void deleteStudent(Integer id) {
        int rows = studentRepository.delete(id);

        if(rows == 0) {
            throw new ResourceNotFoundException("Student not found or access denied");
        }
    }
    public List<Student> searchStudents(String keyword, int page, int size) {

        int offset = page * size;

        return studentRepository.searchStudents(keyword, size, offset);
    }

    public List<StudentCourseDTO> getCoursesByStudent(Integer studentId) {

        if(studentId == null){
            throw new IllegalArgumentException("Student ID cannot be null");
        }

        return enrollmentRepository.getCoursesByStudentId(studentId);
    }
}

