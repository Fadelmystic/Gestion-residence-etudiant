package com.example.prj.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "maintenance")
public class Maintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_id")
    @JsonBackReference
    private Room room;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonBackReference
    private Student student;

    @ManyToOne
    @JoinColumn(name = "technician_id")
    @JsonBackReference
    private Technician technician;

    private String description;
    private LocalDateTime reportedDate;
    private LocalDateTime resolvedDate;

    @Enumerated(EnumType.STRING)
    private MaintenanceStatus status;

    private String priority;
    private String notes;

    public Maintenance() {
    }

    public Maintenance(Long id, Room room, Student student, Technician technician, String description, LocalDateTime reportedDate, LocalDateTime resolvedDate, MaintenanceStatus status, String priority, String notes) {
        this.id = id;
        this.room = room;
        this.student = student;
        this.technician = technician;
        this.description = description;
        this.reportedDate = reportedDate;
        this.resolvedDate = resolvedDate;
        this.status = status;
        this.priority = priority;
        this.notes = notes;
    }

    public Maintenance(String priority, String description, MaintenanceStatus status, Object o) {
        this.priority = priority;
        this.description = description;
        this.status = status;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Technician getTechnician() {
        return technician;
    }

    public void setTechnician(Technician technician) {
        this.technician = technician;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getReportedDate() {
        return reportedDate;
    }

    public void setReportedDate(LocalDateTime reportedDate) {
        this.reportedDate = reportedDate;
    }

    public LocalDateTime getResolvedDate() {
        return resolvedDate;
    }

    public void setResolvedDate(LocalDateTime resolvedDate) {
        this.resolvedDate = resolvedDate;
    }

    public MaintenanceStatus getStatus() {
        return status;
    }

    public void setStatus(MaintenanceStatus status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setTechnicianId(Long technicianId) {

    }
}