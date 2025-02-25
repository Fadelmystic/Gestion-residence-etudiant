package com.example.prj.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonManagedReference
    private Student student;

    @ManyToOne
    @JoinColumn(name = "room_id")
    @JsonManagedReference
    private Room room;  // Ajouter la relation ManyToOne avec la classe Room

    private BigDecimal amount;
    private LocalDateTime paymentDate;
    private String paymentMethod;
    private String receiptNumber;
    private boolean isPaid;
    private LocalDateTime dueDate;
    private PayementStatus status;
    // Constructeur avec Room et Student
    public Payment(Student student, Room room, BigDecimal amount, LocalDateTime paymentDate, String paymentMethod, String receiptNumber, boolean isPaid, LocalDateTime dueDate, PayementStatus status) {
        this.student = student;
        this.room = room;  // Initialiser la chambre
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.receiptNumber = receiptNumber;
        this.isPaid = isPaid;
        this.dueDate = dueDate;
        this.status = status;
    }

    public Payment(Long id, String roomNumber, BigDecimal amount, LocalDateTime paymentDate,
                   String paymentMethod, String receiptNumber, boolean isPaid,
                   LocalDateTime dueDate, PayementStatus status) {
        this.student = new Student(id);;
        this.room = new Room(roomNumber);
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.receiptNumber = receiptNumber;
        this.isPaid = isPaid;
        this.dueDate = dueDate;
        this.status = status;
    }

    // Constructeur avec ID
    public Payment(Long id, Student student, Room room, BigDecimal amount, LocalDateTime paymentDate, String paymentMethod, String receiptNumber, boolean isPaid, LocalDateTime dueDate,PayementStatus status) {
        this.id = id;
        this.student = student;
        this.room = room;  // Initialiser la chambre
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.receiptNumber = receiptNumber;
        this.isPaid = isPaid;
        this.dueDate = dueDate;
        this.status = status;
    }

    // Constructeur par défaut
    public Payment() {

    }

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public PayementStatus getStatus() {
        return status;
    }

    public void setStatus(PayementStatus status) {
        this.status = status;
    }
}
