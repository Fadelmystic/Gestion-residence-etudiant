package com.example.prj.dto;

import com.example.prj.entity.MaintenanceStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public class MaintenanceReport {
    private Long id;
    private String priority;
    private MaintenanceStatus status;
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime reportedDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime resolvedDate;
    private Long roomId;

    public MaintenanceReport() {
    }
    public MaintenanceReport(Long id, String priority, MaintenanceStatus status, String description, LocalDateTime reportedDate, LocalDateTime resolvedDate) {
        this.id = id;
        this.priority = priority;
        this.status = status;
        this.description = description;
        this.reportedDate = reportedDate;
        this.resolvedDate = resolvedDate;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public MaintenanceStatus getStatus() {
        return status;
    }

    public void setStatus(MaintenanceStatus status) {
        this.status = status;
    }


    public void setStatus(String status) {
        this.status = MaintenanceStatus.valueOf(status);
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

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "MaintenanceReport{" +
                "id=" + id +
                ", priority='" + priority + '\'' +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                ", reportedDate=" + reportedDate +
                ", resolvedDate=" + resolvedDate +
                '}';
    }
}