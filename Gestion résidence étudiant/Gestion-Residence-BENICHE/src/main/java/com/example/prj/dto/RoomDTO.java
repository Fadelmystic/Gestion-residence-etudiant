package com.example.prj.dto;

import com.example.prj.entity.RoomStatus;
public class RoomDTO {

    private Long id;
    private String roomNumber;
    private double size;
    private RoomStatus status;
    private Long studentId;

    public RoomDTO() {
    }


    public RoomDTO(String roomNumber, double size, RoomStatus status, Long studentId) {
        this.roomNumber = roomNumber;
        this.size = size;
        this.status = status;
        this.studentId = studentId;
    }

    public RoomDTO(Long id, String roomNumber, double size, RoomStatus status, Long studentId) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.size = size;
        this.status = status;
        this.studentId = studentId;
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

    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "RoomDTO{" +
                "id=" + id +
                ", roomNumber='" + roomNumber + '\'' +
                ", size=" + size +
                ", status=" + status +
                ", studentId=" + studentId +
                '}';
    }
}