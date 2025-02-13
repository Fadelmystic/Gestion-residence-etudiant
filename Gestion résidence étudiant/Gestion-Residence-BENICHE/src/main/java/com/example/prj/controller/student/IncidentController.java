package com.example.prj.controller.student;


import com.example.prj.dto.MaintenanceDTO;
import com.example.prj.entity.Maintenance;
import com.example.prj.entity.Student;
import com.example.prj.service.MaintenanceService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/incidents")
public class IncidentController {

    @Autowired
    private MaintenanceService maintenanceService;

    @GetMapping("/me")
    public ResponseEntity<?> getMyIncidents(HttpSession session){
        if(session.getAttribute("student") != null){
            Student student = (Student) session.getAttribute("student");
            List<Maintenance> maintenances = maintenanceService.getMaintenanceByStudentId(student.getId());
            return ResponseEntity.ok(maintenances);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You need to be logged in");
        }
    }

    @PostMapping
    public ResponseEntity<?> addIncident(@RequestBody MaintenanceDTO maintenanceDTO, HttpSession session) {
        if (session.getAttribute("student") != null) {
            Student student = (Student) session.getAttribute("student");
            try {
                Maintenance maintenance = maintenanceService.createMaintenance(
                        maintenanceDTO.getType(),
                        maintenanceDTO.getDescription(),
                        student.getId(),
                        maintenanceDTO.getRoomId()
                );
                return ResponseEntity.ok().build();
            } catch (Exception e) {
                return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur est survenue");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You need to be logged in");
        }
    }

}