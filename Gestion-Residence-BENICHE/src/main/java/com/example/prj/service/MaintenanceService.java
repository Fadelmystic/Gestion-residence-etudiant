package com.example.prj.service;

import com.example.prj.dto.MaintenanceReport;
import com.example.prj.entity.Maintenance;
import com.example.prj.entity.MaintenanceStatus;
import com.example.prj.entity.Room;
import com.example.prj.entity.Student;
import com.example.prj.repository.MaintenanceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceService {
    private final MaintenanceRepository maintenanceRepository;
    private final StudentService studentService;
    private final RoomService roomService;

    public MaintenanceService(MaintenanceRepository maintenanceRepository, StudentService studentService, RoomService roomService) {
        this.maintenanceRepository = maintenanceRepository;
        this.studentService = studentService;
        this.roomService = roomService;
    }

    // Créer une demande de maintenance
    public Maintenance createRequest(Maintenance maintenance) {
        return maintenanceRepository.save(maintenance);
    }

    // Mettre à jour une demande de maintenance
    public Maintenance updateRequest(Long id, Maintenance maintenance) {
        Maintenance existing = maintenanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Maintenance request not found"));

        existing.setStatus(maintenance.getStatus());
        existing.setNotes(maintenance.getNotes());
        existing.setTechnician(maintenance.getTechnician());
        existing.setResolvedDate(maintenance.getResolvedDate());

        return maintenanceRepository.save(existing);
    }

    // Récupérer les demandes de maintenance par ID de chambre
    public List<Maintenance> getRequestsByRoom(Long roomId) {
        return maintenanceRepository.findByRoomId(roomId);
    }

    // Récupérer les demandes de maintenance par ID d'étudiant
    public List<Maintenance> getRequestsByStudent(Long studentId) {
        return maintenanceRepository.findByStudentId(studentId);
    }

    // Supprimer une demande de maintenance par ID
    public void deleteRequest(Long id) {
        maintenanceRepository.deleteById(id);
    }

    // Récupérer toutes les demandes de maintenance
    public List<Maintenance> getAllRequests() {
        return maintenanceRepository.findAll();
    }

    // Récupérer les demandes de maintenance non résolues
    public List<Maintenance> getUnresolvedRequests() {
        return maintenanceRepository.findByStatusNot("RESOLVED");
    }

    // Récupérer les demandes de maintenance résolues
    public List<Maintenance> getResolvedRequests() {
        return maintenanceRepository.findByStatus(MaintenanceStatus.valueOf("RESOLVED"));
    }

    // Assigner un technicien à une demande de maintenance
    public Maintenance assignTechnician(Long requestId, Long technicianId) {
        Maintenance maintenance = maintenanceRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Maintenance request not found"));

        // Assume there's a Technician entity and assign it to the maintenance
        // maintenance.setTechnician(technician);  // Technicien should be fetched based on technicianId
        return maintenanceRepository.save(maintenance);
    }

    // Marquer une demande de maintenance comme résolue
    public Maintenance markAsResolved(Long id) {
        Maintenance maintenance = maintenanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Maintenance request not found"));

        maintenance.setStatus(MaintenanceStatus.valueOf("RESOLVED"));
        return maintenanceRepository.save(maintenance);
    }

    // Récupérer les demandes de maintenance dans une plage de dates
    public List<Maintenance> getRequestsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return maintenanceRepository.findByRequestDateBetween(startDate, endDate);
    }

    public List<Maintenance> getRequests() {
        return maintenanceRepository.findAll();
    }

    public List<Maintenance> getMaintenanceByStudentId(Long studentId) {
        return maintenanceRepository.findByStudentId(studentId);
    }

    public Maintenance createMaintenance(String type, String description, Long studentId, Long roomId) {
        Student student = studentService.getStudent(studentId);
        Room room = roomService.getRoomById(roomId);
        Maintenance maintenance = new Maintenance();
        maintenance.setDescription(description);
        maintenance.setReportedDate(LocalDateTime.now());
        maintenance.setStudent(student);
        maintenance.setRoom(room);
        maintenance.setPriority(type);
        maintenance.setStatus(MaintenanceStatus.PENDING);
        maintenance.setTechnician(null);
        return maintenanceRepository.save(maintenance);
    }

    public Maintenance updateMaintenanceM(Long id, MaintenanceReport maintenanceDTO) {
        Optional<Maintenance> existingMaintenanceOptional = maintenanceRepository.findById(id);

        if (existingMaintenanceOptional.isPresent()) {
            Maintenance existingMaintenance = existingMaintenanceOptional.get();
            existingMaintenance.setPriority(maintenanceDTO.getPriority());
            existingMaintenance.setDescription(maintenanceDTO.getDescription());
            existingMaintenance.setStatus(maintenanceDTO.getStatus());
            if (maintenanceDTO.getRoomId() != null) {
                Room room = roomService.getRoomById(maintenanceDTO.getRoomId());
                existingMaintenance.setRoom(room);
            } else {
                existingMaintenance.setRoom(null);
            }
            existingMaintenance.setId(id);
            return maintenanceRepository.save(existingMaintenance);

        } else {
            throw new RuntimeException("Maintenance with ID " + id + " not found!");
        }
    }
    public Maintenance getMaintenanceById(Long id) {
        return maintenanceRepository.findById(id).orElseThrow(() -> new RuntimeException("Maintenance with ID " + id + " not found!"));
    }

    public Maintenance createRequestM(MaintenanceReport registerRequest) {
        Maintenance maintenance = new Maintenance(
                registerRequest.getPriority(),
                registerRequest.getDescription(),
                registerRequest.getStatus(),
                null // to avoid null pointer exception
        );
        if(registerRequest.getRoomId() != null){
            Room room = roomService.getRoomById(registerRequest.getRoomId());
            maintenance.setRoom(room);
        }
        return maintenanceRepository.save(maintenance);
    }
}
