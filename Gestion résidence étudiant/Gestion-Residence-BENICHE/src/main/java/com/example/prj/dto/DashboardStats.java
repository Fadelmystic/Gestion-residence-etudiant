package com.example.prj.dto;

import lombok.Data;

@Data
public class DashboardStats {
    private int totalRooms;
    private int occupiedRooms;
    private int availableRooms;
    private int totalStudents;
    private int pendingPayments;
    private int activeIncidents;
    private double occupancyRate;
    private double monthlyRevenue;

    public DashboardStats(int totalRooms, int occupiedRooms, int availableRooms, int totalStudents, int pendingPayments, int activeIncidents, double occupancyRate, double monthlyRevenue) {
        this.totalRooms = totalRooms;
        this.occupiedRooms = occupiedRooms;
        this.availableRooms = availableRooms;
        this.totalStudents = totalStudents;
        this.pendingPayments = pendingPayments;
        this.activeIncidents = activeIncidents;
        this.occupancyRate = occupancyRate;
        this.monthlyRevenue = monthlyRevenue;
    }
    public DashboardStats() {}

    public int getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(int totalStudents) {
        this.totalStudents = totalStudents;
    }

    public int getTotalRooms() {
        return totalRooms;
    }

    public void setTotalRooms(int totalRooms) {
        this.totalRooms = totalRooms;
    }

    public int getOccupiedRooms() {
        return occupiedRooms;
    }

    public void setOccupiedRooms(int occupiedRooms) {
        this.occupiedRooms = occupiedRooms;
    }

    public int getAvailableRooms() {
        return availableRooms;
    }

    public void setAvailableRooms(int availableRooms) {
        this.availableRooms = availableRooms;
    }

    public int getPendingPayments() {
        return pendingPayments;
    }

    public void setPendingPayments(int pendingPayments) {
        this.pendingPayments = pendingPayments;
    }

    public int getActiveIncidents() {
        return activeIncidents;
    }

    public void setActiveIncidents(int activeIncidents) {
        this.activeIncidents = activeIncidents;
    }

    public double getOccupancyRate() {
        return occupancyRate;
    }

    public void setOccupancyRate(double occupancyRate) {
        this.occupancyRate = occupancyRate;
    }

    public double getMonthlyRevenue() {
        return monthlyRevenue;
    }

    public void setMonthlyRevenue(double monthlyRevenue) {
        this.monthlyRevenue = monthlyRevenue;
    }
}
