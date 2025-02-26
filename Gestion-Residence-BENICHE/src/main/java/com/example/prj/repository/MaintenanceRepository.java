package com.example.prj.repository;

import com.example.prj.entity.Maintenance;
import com.example.prj.entity.MaintenanceStatus;
import com.example.prj.entity.Room;
import com.example.prj.entity.Technician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {
    @Query("SELECT m FROM Maintenance m WHERE m.room.id = :roomId")
    List<Maintenance> findByRoomId(@Param("roomId") Long roomId);

    @Query("SELECT m FROM Maintenance m WHERE m.student.id = :studentId")
    List<Maintenance> findByStudentId(@Param("studentId") Long studentId);

    @Query("SELECT m FROM Maintenance m WHERE m.technician.id = :technicianId")
    List<Maintenance> findByTechnicianId(@Param("technicianId") Long technicianId);


        // Compter le nombre d'incidents actifs
        @Query("SELECT COUNT(i) FROM Maintenance i WHERE i.status = 'IN_PROGRESS'")
        int countActiveIncidents();

    @Query("SELECT m FROM Maintenance m WHERE m.technician IS NULL")
    List<Maintenance> findByTechnicianIdIsNull();


    @Query("SELECT m FROM Maintenance m WHERE m.technician.id = :technicianId AND m.status = :maintenanceStatus")
    List<Maintenance> findByTechnicianIdAndStatus(@Param("technicianId") Long technicianId, @Param("maintenanceStatus") MaintenanceStatus maintenanceStatus);


    @Query("SELECT m FROM Maintenance m WHERE m.status = :maintenanceStatus")
    List<Maintenance> findByStatus(@Param("maintenanceStatus") MaintenanceStatus maintenanceStatus);


    @Query("SELECT m FROM Maintenance m WHERE m.status <> :resolved")
    List<Maintenance> findByStatusNot(@Param("resolved") String resolved);


    @Query("SELECT m FROM Maintenance m WHERE m.reportedDate BETWEEN :startDate AND :endDate")
    List<Maintenance> findByRequestDateBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT m FROM Maintenance m WHERE m.technician.id = :id")
    List<Maintenance> findByTechnician(@Param("id") Long id);

    List<Room> findByStudent_Id(Long studentId);
}