package com.example.prj.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "technicians")
public class Technician {


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

    private boolean available;

    @OneToMany(mappedBy = "technician")
    @JsonManagedReference
    private List<Maintenance> assignments;

    public Technician(String password, String email) {
        this.email = email;
        this.password = password;
    }

    public Technician(Long id, String username, String password, String firstName, String lastName, String email, LocalDate dateNaissance, boolean available, List<Maintenance> assignments) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateNaissance = dateNaissance;
        this.available = available;
        this.assignments = assignments;
    }
    public Technician() {}

    public Technician(String username, String password, String firstName, String lastName, String email, LocalDate dateNaissance, boolean available, List<Maintenance> assignments) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateNaissance = dateNaissance;
        this.available = available;
        this.assignments = assignments;
    }



    public Technician(String username, String password, String firstName, String lastName, String email, LocalDate dateNaissance, boolean disponible) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateNaissance = dateNaissance;
        this.available = disponible;
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

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public List<Maintenance> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Maintenance> assignments) {
        this.assignments = assignments;
    }

    public boolean getAvailable() {
        return available;
    }
}
