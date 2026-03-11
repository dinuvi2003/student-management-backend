package com.assignment.studentmanagement.dto;

import java.time.LocalDate;

public class StudentCourseDTO {

    private Integer courseId;
    private String courseName;
    private String courseDescription;
    private LocalDate enrollmentDate;

    // Constructor
    public StudentCourseDTO(Integer courseId, String courseName, String courseDescription, LocalDate enrollmentDate) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.enrollmentDate = enrollmentDate;
    }

    // Getters
    public Integer getCourseId() { return courseId; }
    public String getCourseName() { return courseName; }
    public String getCourseDescription() { return courseDescription; }
    public LocalDate getEnrollmentDate() { return enrollmentDate; }
}
