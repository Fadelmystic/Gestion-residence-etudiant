package com.example.prj.entity;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "admins")
public class Admin {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;


    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dateNaissance;

    public Admin(Long id, String username, String password, String firstName, String lastName, String email, LocalDate dateNaissance) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateNaissance = dateNaissance;
    }
    public Admin(String password, String email) {
        this.email = email;
        this.password = password;
    }
    public Admin() {}
    public Admin(String username, String password, String firstName, String lastName, String email, LocalDate dateNaissance) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateNaissance = dateNaissance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
}
