package com.example.prj.dto;

import com.example.prj.entity.PayementStatus;

import java.math.BigDecimal;

public class PaymentRecord {
    private Long id;
    private BigDecimal amount;
    private String paymentMethod;
    private PayementStatus status;
    private Long roomId;
    private Long studentId;

    public PaymentRecord(){}
    public PaymentRecord(Long id, BigDecimal amount, String paymentMethod, PayementStatus status, Long roomId, Long studentId) {
        this.id = id;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.roomId = roomId;
        this.studentId = studentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PayementStatus getStatus() {
        return status;
    }

    public void setStatus(PayementStatus status) {
        this.status = status;
    }

    public void setStatus(String status) {
        this.status = PayementStatus.valueOf(status);
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }


    @Override
    public String toString() {
        return "PaymentDTO{" +
                "id=" + id +
                ", amount=" + amount +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", status='" + status + '\'' +
                ", roomId=" + roomId +
                ", studentId=" + studentId +
                '}';
    }
}
