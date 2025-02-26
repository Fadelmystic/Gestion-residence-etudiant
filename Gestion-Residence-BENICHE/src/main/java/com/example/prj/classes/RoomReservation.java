package com.example.prj.classes;

import com.example.prj.entity.RoomStatus;
import com.example.prj.entity.Student;


public class RoomReservation {
    private Long id;

    private String roomNumber;
    private double size;
    private boolean available;

    private RoomStatus status;

    private Long student_id;

    public RoomReservation() {}

    public RoomReservation(Long id, String roomNumber, double size, boolean available, RoomStatus status, Long student_id) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.size = size;
        this.available = available;
        this.status = status;
        this.student_id = student_id;
    }

    public RoomReservation(String roomNumber, double size, boolean available, RoomStatus status, Long student_id) {
        this.roomNumber = roomNumber;
        this.size = size;
        this.available = available;
        this.status = status;
        this.student_id = student_id;
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

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }
}
