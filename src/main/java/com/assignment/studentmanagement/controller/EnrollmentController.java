package com.assignment.studentmanagement.controller;

import com.assignment.studentmanagement.service.EnrollmentService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService){
        this.enrollmentService = enrollmentService;
    }

    @DeleteMapping
    public void deleteEnrollment(@RequestParam(required = true) Integer studentId,
                                 @RequestParam(required = true) Integer courseId){

        enrollmentService.removeEnrollment(studentId, courseId);
    }
}
