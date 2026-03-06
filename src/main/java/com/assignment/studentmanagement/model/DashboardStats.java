package com.assignment.studentmanagement.model;

public class DashboardStats {

    private int totalStudents;
    private int enrollmentYears;
    private int enrolledThisYear;
    private int enrolledThisMonth;

    public DashboardStats(int totalStudents, int enrollmentYears, int enrolledThisYear, int enrolledThisMonth) {
        this.totalStudents = totalStudents;
        this.enrollmentYears = enrollmentYears;
        this.enrolledThisYear = enrolledThisYear;
        this.enrolledThisMonth = enrolledThisMonth;
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
}
