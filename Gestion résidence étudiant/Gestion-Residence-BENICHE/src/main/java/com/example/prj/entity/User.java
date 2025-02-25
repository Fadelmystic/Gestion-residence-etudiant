//package com.example.prj.entity;
//
//import jakarta.persistence.*;
//import lombok.Data;
//import java.time.LocalDateTime;
//
//@Data
//@Entity
//@Table(name = "users")
//@Inheritance(strategy = InheritanceType.JOINED)
//public class User {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(unique = true, nullable = false)
//    private String username;
//
//    @Column(nullable = false)
//    private String password;
//
//    @ManyToOne
//    @JoinColumn(name = "role_id", nullable = false)
//    private UserRole userRole;
//
//    private String firstName;
//    private String lastName;
//    private String email;
//    private String phoneNumber;
//    private LocalDateTime createdAt;
//    private boolean active;
//    @Transient
//    private String token; // Token généré lors de l'authentification
//
//
//
//    public User() {
//
//    }
//
//
//    public User(String username, String password, String firstName, String lastName, String email, String phoneNumber) {
//    this.username = username;
//    this.password = password;
//    this.firstName = firstName;
//    this.lastName = lastName;
//    this.email = email;
//    this.phoneNumber = phoneNumber;
//    }
//
//    public User(Long id, String username, String password, String firstName, String lastName, String email, String phoneNumber) {
//        this.id = id;
//        this.username = username;
//        this.password = password;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.phoneNumber = phoneNumber;
//    }
//
//
//    @PrePersist
//    protected void onCreate() {
//        createdAt = LocalDateTime.now();
//        active = true;
//    }
//
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public UserRole getUserRole() {
//        return userRole;
//    }
//
//    public void setUserRole(UserRole userRole) {
//        this.userRole = userRole;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
//
//    public LocalDateTime getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(LocalDateTime createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public boolean isActive() {
//        return active;
//    }
//
//    public void setActive(boolean active) {
//        this.active = active;
//    }
//    public String getToken() {
//        return "user-token-" + id; // Simplification pour l'exemple
//    }
//}