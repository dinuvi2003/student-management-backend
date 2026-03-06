package com.assignment.studentmanagement.controller;

import com.assignment.studentmanagement.model.DashboardStats;
import com.assignment.studentmanagement.security.CustomUserDetails;
import com.assignment.studentmanagement.service.DashboardService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping
    public DashboardStats getDashboardStats(Authentication authentication) {

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Integer adminId = userDetails.getAdminId();

        return dashboardService.getDashboardStats(adminId);
    }
}
