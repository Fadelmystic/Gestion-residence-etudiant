package com.example.prj.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomNumber;
    private double size;
    private boolean available;

    @Enumerated(EnumType.STRING)
    private RoomStatus status;

    @OneToOne
    @JoinColumn(name = "student_id")
    @JsonBackReference // Eviter la boucle infinie lors de la sérialisation
    private Student student;


    @OneToMany(mappedBy = "room")
    @JsonManagedReference
    private List<Maintenance> maintenanceRequests;

    public Room(Long id, String roomNumber, double size, boolean available, RoomStatus status, Student student, List<Maintenance> maintenanceRequests) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.size = size;
        this.available = available;
        this.status = status;
        this.student = student;
        this.maintenanceRequests = maintenanceRequests;
    }

    public Room() {}

    public Room(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Room(String roomNumber, double size, RoomStatus status, Object o) {
        this.roomNumber = roomNumber;
        this.size = size;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Maintenance> getMaintenanceRequests() {
        return maintenanceRequests;
    }

    public void setMaintenanceRequests(List<Maintenance> maintenanceRequests) {
        this.maintenanceRequests = maintenanceRequests;
    }


    public void setAssignedStudentId(Long studentId) {

    }
}