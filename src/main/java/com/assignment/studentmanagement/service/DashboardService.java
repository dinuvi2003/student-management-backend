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

    public DashboardStats getDashboardStats(Integer adminId) {

        int totalStudents = dashboardRepository.getTotalStudents(adminId);
        int enrollmentYears = dashboardRepository.getEnrollmentYears(adminId);
        int enrolledThisYear = dashboardRepository.getEnrolledThisYear(adminId);
        int enrolledThisMonth = dashboardRepository.getEnrolledThisMonth(adminId);

        return new DashboardStats(
                totalStudents,
                enrollmentYears,
                enrolledThisYear,
                enrolledThisMonth
        );
    }
}
