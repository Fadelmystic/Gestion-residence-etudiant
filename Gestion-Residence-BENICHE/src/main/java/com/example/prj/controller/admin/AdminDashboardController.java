package com.example.prj.controller.admin;

import com.example.prj.dto.DashboardStats;
import com.example.prj.service.AdminDashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/dashboard")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminDashboardController {

    private final AdminDashboardService adminDashboardService;

    public AdminDashboardController(AdminDashboardService adminDashboardService) {
        this.adminDashboardService = adminDashboardService;
    }

    @GetMapping("/stats")
    public ResponseEntity<DashboardStats> getDashboardStats() {
        return ResponseEntity.ok(adminDashboardService.getDashboardStats());
    }
}