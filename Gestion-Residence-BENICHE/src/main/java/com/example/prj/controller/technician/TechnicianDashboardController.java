package com.example.prj.controller.technician;

import com.example.prj.entity.Maintenance;
import com.example.prj.entity.Technician;
import com.example.prj.service.TechnicianDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/technician")

@CrossOrigin(origins = "http://localhost:3000")
public class TechnicianDashboardController {
    private final TechnicianDashboardService technicianDashboardService;

    public TechnicianDashboardController(TechnicianDashboardService technicianDashboardService) {
        this.technicianDashboardService = technicianDashboardService;
    }

    @GetMapping("/available-incidents")
    public ResponseEntity<List<Maintenance>> getAvailableIncidents() {
        return ResponseEntity.ok(technicianDashboardService.getAvailableIncidents());
    }

    @PostMapping("/accept-incident/{incidentId}")
    public ResponseEntity<Maintenance> acceptIncident(
            @PathVariable Long incidentId, 
            @RequestParam Long technicianId) {
        return ResponseEntity.ok(technicianDashboardService.acceptIncident(incidentId, technicianId));
    }

    @GetMapping("/my-incidents/{technicianId}")
    public ResponseEntity<List<Maintenance>> getMyIncidents(@PathVariable Technician technician) {
        return ResponseEntity.ok(technicianDashboardService.getTechnicianIncidents(technician));
    }

    @PutMapping("/update-incident-status/{incidentId}")
    public ResponseEntity<Maintenance> updateIncidentStatus(
            @PathVariable Long incidentId, 
            @RequestBody Maintenance maintenance) {
        return ResponseEntity.ok(technicianDashboardService.updateIncidentStatus(incidentId, String.valueOf(maintenance)));
    }
}