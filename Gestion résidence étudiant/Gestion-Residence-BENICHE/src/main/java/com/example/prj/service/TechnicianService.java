package com.example.prj.service;


import com.example.prj.classes.RegisterRequest;
import com.example.prj.dto.TechnicainDTO;
import com.example.prj.entity.Student;
import com.example.prj.entity.Technician;
import com.example.prj.repository.TechnicianRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TechnicianService {

private final TechnicianRepository technicianRepository;

public TechnicianService(TechnicianRepository technicianRepository) {
    this.technicianRepository = technicianRepository;

}

// Retrieve all students
public List<Technician> getAllTechnicians() {
    List<Technician> result = technicianRepository.findAll();
    return result;
}

// Retrieve a single student by ID
public Technician getTechnician(Long id) {
    Optional<Technician> technician = Optional.of(technicianRepository.findByUserId((id)));
    return technician.orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));
}

// Create a new student
public Technician createTechnician(Technician technician) {
    return technicianRepository.save(technician);
}

// Update an existing student
public Technician updateTechnician(Long id, Technician technicianDetails) {
    Technician existingTechnician = (Technician) getTechnician(id);
    existingTechnician.setUsername(technicianDetails.getUsername());
    existingTechnician.setLastName(technicianDetails.getLastName());
    existingTechnician.setFirstName(technicianDetails.getFirstName());
    existingTechnician.setDateNaissance(technicianDetails.getDateNaissance());
    existingTechnician.setEmail(technicianDetails.getEmail());
    existingTechnician.setPassword(technicianDetails.getPassword());
    existingTechnician.setAvailable(technicianDetails.getAvailable());
    // Add other fields as needed
    return technicianRepository.save(existingTechnician);
}


    // Delete a student
public void deleteTechnician(Long id) {
    Technician technician = (Technician) getTechnician(id);
    technicianRepository.delete(technician);
}



    public Technician createTechnicianR(TechnicainDTO registerRequest) {

        Technician technician = new Technician(
                registerRequest.getUsername(),
                registerRequest.getPassword(),
                registerRequest.getFirstName(),
                registerRequest.getLastName(),
                registerRequest.getEmail(),
                registerRequest.getDateNaissance(),
                registerRequest.getDisponible()

        );

        return technicianRepository.save(technician);
    }

    public Technician updateTechnicianT(Long id, TechnicainDTO technician) {
        Optional<Technician> existingTechnicianOptional = technicianRepository.findById(id);

        if (existingTechnicianOptional.isPresent()) {
            Technician existingTechnician = existingTechnicianOptional.get();
            existingTechnician.setFirstName(technician.getFirstName());
            existingTechnician.setLastName(technician.getLastName());
            existingTechnician.setUsername(technician.getUsername());
            existingTechnician.setEmail(technician.getEmail());
            existingTechnician.setPassword(technician.getPassword());
            existingTechnician.setDateNaissance(technician.getDateNaissance());
            existingTechnician.setAvailable(technician.getDisponible());
            return technicianRepository.save(existingTechnician);
        } else {
            throw new RuntimeException("Technician with ID " + id + " not found!");
        }
    }
}
