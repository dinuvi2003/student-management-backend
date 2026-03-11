package com.assignment.studentmanagement.service;

import com.assignment.studentmanagement.model.DashboardStats;
import com.assignment.studentmanagement.repository.DashboardRepository;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private final DashboardRepository dashboardRepository;

    public DashboardService(DashboardRepository dashboardRepository) {
        this.dashboardRepository = dashboardRepository;
    }

    public DashboardStats getDashboardStats() {

        int totalStudents = dashboardRepository.getTotalStudents();
        int enrollmentYears = dashboardRepository.getEnrollmentYears();
        int enrolledThisYear = dashboardRepository.getEnrolledThisYear();
        int enrolledThisMonth = dashboardRepository.getEnrolledThisMonth();
        int totalCourses = dashboardRepository.getTotalCourses();
        String mostEnrolledCourse = dashboardRepository.getMostEnrolledCourse();

        return new DashboardStats(
                totalStudents,
                enrollmentYears,
                enrolledThisYear,
                enrolledThisMonth,
                totalCourses,
                mostEnrolledCourse
        );
    }
}
