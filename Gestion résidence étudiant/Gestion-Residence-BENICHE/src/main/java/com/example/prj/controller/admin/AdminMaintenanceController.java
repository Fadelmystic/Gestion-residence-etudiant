package com.example.prj.controller.admin;

import com.example.prj.dto.MaintenanceDTO;
import com.example.prj.dto.MaintenanceReport;
import com.example.prj.entity.Maintenance;
import com.example.prj.service.MaintenanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/maintenance")

@CrossOrigin(origins = "http://localhost:3000")
public class AdminMaintenanceController {
    private final MaintenanceService maintenanceService;

    public AdminMaintenanceController(MaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    @GetMapping
    public ResponseEntity<List<Maintenance>> getRequests() {
        return ResponseEntity.ok(maintenanceService.getRequests());
    }

    @GetMapping("/room/{roomId}")
    public ResponseEntity<List<Maintenance>> getRequestsByRoom(@PathVariable Long roomId) {
        return ResponseEntity.ok(maintenanceService.getRequestsByRoom(roomId));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Maintenance>> getRequestsByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(maintenanceService.getRequestsByStudent(studentId));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Maintenance> getMaintenance(@PathVariable Long id) {
        return ResponseEntity.ok(maintenanceService.getMaintenanceById(id));
    }

//    @PostMapping
//    public ResponseEntity<Maintenance> createRequest(@RequestBody Maintenance maintenance) {
//        return ResponseEntity.ok(maintenanceService.createRequest(maintenance));
//    }
    @PostMapping
    public ResponseEntity<Maintenance> createRequest(@RequestBody MaintenanceReport maintenanceReport) {
        return ResponseEntity.ok(maintenanceService.createRequestM(maintenanceReport));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Maintenance> updateMaintenance(@PathVariable Long id, @RequestBody MaintenanceReport maintenanceReport ) {
        return ResponseEntity.ok(maintenanceService.updateMaintenanceM(id, maintenanceReport));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {
        maintenanceService.deleteRequest(id);
        return ResponseEntity.ok().build();
    }
}