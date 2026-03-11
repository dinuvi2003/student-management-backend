package com.assignment.studentmanagement.dto;

import com.assignment.studentmanagement.model.Student;

import java.time.LocalDate;
import java.util.List;

public class CreateStudentRequest {

    private Student student;
    private List<Integer> courseIds;
    private List<LocalDate> enrollmentDates;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Integer> getCourseIds() {
        return courseIds;
    }

    public void setCourseIds(List<Integer> courseIds) {
        this.courseIds = courseIds;
    }

    public List<LocalDate> getEnrollmentDates() { return enrollmentDates; }
    public void setEnrollmentDates(List<LocalDate> enrollmentDates) { this.enrollmentDates = enrollmentDates; }
}
