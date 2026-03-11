package com.assignment.studentmanagement.dto;

import java.time.LocalDate;

public class CourseStudentDTO {

    private Integer studentId;
    private String firstName;
    private String lastName;
    private LocalDate enrollmentDate;

    public CourseStudentDTO(Integer studentId, String firstName, String lastName, LocalDate enrollmentDate) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.enrollmentDate = enrollmentDate;
    }

    public Integer getStudentId() { return studentId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public LocalDate getEnrollmentDate() { return enrollmentDate; }
}
