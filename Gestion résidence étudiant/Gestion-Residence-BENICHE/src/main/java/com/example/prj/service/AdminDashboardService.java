package com.example.prj.service;

import com.example.prj.dto.DashboardStats;
import com.example.prj.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AdminDashboardService {

    private final RoomRepository roomRepository;
    private final StudentRepository studentRepository;
    private final PaymentRepository paymentRepository;
    private final MaintenanceRepository maintenanceRepository;

    public AdminDashboardService(RoomRepository roomRepository, StudentRepository studentRepository, PaymentRepository paymentRepository, MaintenanceRepository maintenanceRepository) {
        this.roomRepository = roomRepository;
        this.studentRepository = studentRepository;
        this.paymentRepository = paymentRepository;
        this.maintenanceRepository = maintenanceRepository;
    }


    public DashboardStats getDashboardStats() {
        DashboardStats stats = new DashboardStats();

        // Calculer les statistiques
        stats.setTotalRooms(roomRepository.countTotalRooms());
        stats.setOccupiedRooms(roomRepository.countOccupiedRooms());
        stats.setAvailableRooms(stats.getTotalRooms() - stats.getOccupiedRooms());
        stats.setTotalStudents(studentRepository.countTotalStudents());
        stats.setPendingPayments(paymentRepository.countPendingPayments());
        stats.setActiveIncidents(maintenanceRepository.countActiveIncidents());
        stats.setOccupancyRate(calculateOccupancyRate(stats.getTotalRooms(), stats.getOccupiedRooms()));
        stats.setMonthlyRevenue(paymentRepository.calculateMonthlyRevenue());

        // Retourner les statistiques sous forme de tableau
        return stats;
    }

    private double calculateOccupancyRate(int totalRooms, int occupiedRooms) {
        if (totalRooms == 0) {
            return 0.0;
        }
        return ((double) occupiedRooms / totalRooms) * 100;
    }
}
