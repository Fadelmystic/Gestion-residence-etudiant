package com.example.prj.service;

import com.example.prj.entity.Maintenance;
import com.example.prj.entity.MaintenanceStatus;
import com.example.prj.entity.Technician;
import com.example.prj.repository.MaintenanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnicianDashboardService {
    private final MaintenanceRepository maintenanceRepository;

    @Autowired
    public TechnicianDashboardService(MaintenanceRepository maintenanceRepository) {
        this.maintenanceRepository = maintenanceRepository;
    }
    // Récupérer les incidents disponibles (non affectés)
    public List<Maintenance> getAvailableIncidents() {
        return maintenanceRepository.findByTechnicianIdIsNull(); // Chercher les incidents non affectés
    }

    // Accepter un incident par un technicien
    public Maintenance acceptIncident(Long incidentId, Long technicianId) {
        Maintenance incident = maintenanceRepository.findById(incidentId)
                .orElseThrow(() -> new RuntimeException("Incident non trouvé"));

        // Affecter l'incident au technicien
        incident.setTechnicianId(technicianId);
        incident.setStatus(MaintenanceStatus.valueOf("IN_PROGRESS"));

        return maintenanceRepository.save(incident); // Sauvegarder les changements
    }

    // Récupérer les incidents d'un technicien spécifique
    public List<Maintenance> getTechnicianIncidents(Technician technician) {
        return maintenanceRepository.findByTechnician(technician.getId()); // Trouver les incidents du technicien
    }

    // Mettre à jour le statut d'un incident
    public Maintenance updateIncidentStatus(Long incidentId, String status) {
        Maintenance incident = maintenanceRepository.findById(incidentId)
                .orElseThrow(() -> new RuntimeException("Incident non trouvé"));

        // Mettre à jour le statut de l'incident
        incident.setStatus(MaintenanceStatus.valueOf(status));

        return maintenanceRepository.save(incident); // Sauvegarder les changements
    }

    // Libérer un incident par un technicien
    public Maintenance unassignIncident(Long incidentId, Long technicianId) {
        Maintenance incident = maintenanceRepository.findById(incidentId)
                .orElseThrow(() -> new RuntimeException("Incident non trouvé"));

        if (!technicianId.equals(incident.getTechnician().getId())) {
            throw new RuntimeException("Le technicien ne peut pas libérer cet incident");
        }

        incident.setTechnicianId(null); // Supprimer l'affectation
        incident.setStatus(MaintenanceStatus.valueOf("AVAILABLE")); // Marquer comme disponible

        return maintenanceRepository.save(incident);
    }
    // Récupérer les incidents en cours d'un technicien
    public List<Maintenance> getInProgressIncidents(Long technicianId) {
        return maintenanceRepository.findByTechnicianIdAndStatus(technicianId, MaintenanceStatus.IN_PROGRESS);
    }

    // Marquer un incident comme terminé
    public Maintenance completeIncident(Long incidentId, Long technicianId) {
        Maintenance incident = maintenanceRepository.findById(incidentId)
                .orElseThrow(() -> new RuntimeException("Incident non trouvé"));

        if (!technicianId.equals(incident.getTechnician().getId())) {
            throw new RuntimeException("Le technicien n'a pas la permission de terminer cet incident");
        }

        incident.setStatus(MaintenanceStatus.valueOf("COMPLETED")); // Mettre le statut à 'COMPLETED'

        return maintenanceRepository.save(incident); // Sauvegarder
    }


}
