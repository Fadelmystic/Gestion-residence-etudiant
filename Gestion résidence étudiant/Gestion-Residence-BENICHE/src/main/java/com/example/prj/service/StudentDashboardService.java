package com.example.prj.service;

import com.example.prj.entity.*;
import com.example.prj.repository.RoomRepository;
import com.example.prj.repository.PaymentRepository;
import com.example.prj.repository.MaintenanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service

public class StudentDashboardService {
    private final RoomRepository roomRepository;
    private final PaymentRepository paymentRepository;
    private final MaintenanceRepository maintenanceRepository;

    public StudentDashboardService(RoomRepository roomRepository, PaymentRepository paymentRepository, MaintenanceRepository maintenanceRepository) {
        this.roomRepository = roomRepository;
        this.paymentRepository = paymentRepository;
        this.maintenanceRepository = maintenanceRepository;
    }

    public List<Room> getAvailableRooms() {
        return roomRepository.findByAvailable(true);
    }

    public Room selectRoom(Long roomId, Long studentId) {
        // Implementation for room selection logic
        return null;
    }

    public List<Payment> getPaymentHistory(Long studentId) {
        return paymentRepository.findByStudentId(studentId);
    }

    public Maintenance reportIncident(Maintenance incident) {
        incident.setStatus(MaintenanceStatus.PENDING);
        return maintenanceRepository.save(incident);
    }
    // Retrieve room details by ID
    public Room getRoomDetails(Long roomId) {
        return roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found with ID: " + roomId));
    }

    // Check if room is available
    public boolean isRoomAvailable(Long roomId) {
        Room room = getRoomDetails(roomId);
        return room.isAvailable();
    }

    // Make payment for room reservation
    public Payment makePayment(Student student, Room room, BigDecimal amount) {
        Payment payment = new Payment();
        payment.setStudent(student);
        payment.setRoom(room);
        payment.setAmount(amount);
        payment.setStatus(PayementStatus.PAID);
        return paymentRepository.save(payment);
    }

    // Cancel room reservation
    public void cancelRoomReservation(Long studentId, Long roomId) {
        Room room = getRoomDetails(roomId);
        if (!room.isAvailable()) {
            room.setAvailable(true);
            roomRepository.save(room);
        }
    }

    // Retrieve unresolved incidents
    public List<Maintenance> getUnresolvedIncidents() {
        return maintenanceRepository.findByStatus(MaintenanceStatus.PENDING);
    }

    // Retrieve all reports by a specific student
    public List<Maintenance> getReportsByStudent(Long studentId) {
        return maintenanceRepository.findByStudentId(studentId);
    }

    // Update incident status to "IN_PROGRESS"
    public Maintenance updateIncidentToInProgress(Long incidentId) {
        Maintenance incident = maintenanceRepository.findById(incidentId)
                .orElseThrow(() -> new RuntimeException("Incident not found"));
        incident.setStatus(MaintenanceStatus.IN_PROGRESS);
        return maintenanceRepository.save(incident);
    }

    // Retrieve payment history for a specific period
    public List<Payment> getPaymentsByPeriod(Long studentId, String startDate, String endDate) {
        return paymentRepository.findByStudentIdAndDateBetween(studentId, startDate, endDate);
    }

    // Report room issue
    public Maintenance reportRoomIssue(Student student, Room room, String issueDescription) {
        Maintenance incident = new Maintenance();
        incident.setRoom(room);
        incident.setStudent(student);
        incident.setDescription(issueDescription);
        incident.setStatus(MaintenanceStatus.PENDING);
        return maintenanceRepository.save(incident);
    }




}