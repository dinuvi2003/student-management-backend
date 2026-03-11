package com.assignment.studentmanagement.service;

import com.assignment.studentmanagement.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository){
        this.enrollmentRepository = enrollmentRepository;
    }

    public void removeEnrollment(Integer studentId, Integer courseId){

        int rows = enrollmentRepository.deleteEnrollment(studentId, courseId);

        if(rows == 0){
            throw new RuntimeException("Enrollment not found");
        }
    }

}
