package com.assignment.studentmanagement.model;

public class DashboardStats {

    private int totalStudents;
    private int enrollmentYears;
    private int enrolledThisYear;
    private int enrolledThisMonth;
    private int totalCourses;
    private String mostEnrolledCourse;

    public DashboardStats(int totalStudents, int enrollmentYears, int enrolledThisYear, int enrolledThisMonth, int totalCourses, String mostEnrolledCourse) {
        this.totalStudents = totalStudents;
        this.enrollmentYears = enrollmentYears;
        this.enrolledThisYear = enrolledThisYear;
        this.enrolledThisMonth = enrolledThisMonth;
        this.totalCourses = totalCourses;
        this.mostEnrolledCourse = mostEnrolledCourse;
    }

    public int getTotalStudents() {
        return totalStudents;
    }

    public int getEnrollmentYears() {
        return enrollmentYears;
    }

    public int getEnrolledThisYear() {
        return enrolledThisYear;
    }

    public int getEnrolledThisMonth() {
        return enrolledThisMonth;
    }

    public int getTotalCourses() { return totalCourses; }

    public String getMostEnrolledCourse() { return mostEnrolledCourse; }
}
