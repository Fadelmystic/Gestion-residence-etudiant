package com.example.prj.controller.admin;
import com.example.prj.classes.RegisterRequest;
import com.example.prj.dto.TechnicainDTO;
import com.example.prj.entity.Technician;
import com.example.prj.service.TechnicianService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/admin/technicians")
@CrossOrigin(origins = "http://localhost:3000")

public class AdminTechnicianController {
    private final TechnicianService technicianService;

    public AdminTechnicianController(TechnicianService technicianService) {
        this.technicianService = technicianService;
    }

    @GetMapping
    public ResponseEntity<List<Technician>> getAllTechnician() {
        return ResponseEntity.ok(technicianService.getAllTechnicians());

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTechcnician(@PathVariable Long id) {
        return ResponseEntity.ok(technicianService.getTechnician(id));
    }

    @PostMapping
    public ResponseEntity<Technician> createTechnician(@RequestBody TechnicainDTO technician) {
        return ResponseEntity.ok(technicianService.createTechnicianR(technician));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Technician> updateTechnician(@PathVariable Long id, @RequestBody TechnicainDTO technician ) {

        return ResponseEntity.ok(technicianService.updateTechnicianT(id, technician));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTechnician(@PathVariable Long id) {
        technicianService.deleteTechnician(id);
        return ResponseEntity.ok().build();
    }
}
