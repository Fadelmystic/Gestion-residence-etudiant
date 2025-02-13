package com.example.prj.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;


public class TechnicainDTO {

    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateNaissance;
    private boolean disponible;

    public TechnicainDTO() {
    }

    public TechnicainDTO(Long id, String username, String password, String firstName, String lastName, String email, boolean disponible) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.disponible = disponible;
    }
    public TechnicainDTO(Long id, String username, String password, String firstName, String lastName, String email, LocalDate dateNaissance, boolean disponible) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateNaissance = dateNaissance;
        this.disponible = disponible;
    }
    public TechnicainDTO(String username, String password, String firstName, String lastName, String email, LocalDate dateNaissance, boolean disponible) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateNaissance = dateNaissance;
        this.disponible = disponible;
    }
    public TechnicainDTO(String username, String password, String firstName, String lastName, String email,  boolean disponible) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.disponible = disponible;
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
    public boolean getDisponible() {
        return disponible;
    }
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    @Override
    public String toString() {
        return "TechnicainDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", dateNaissance=" + dateNaissance +
                ", disponible=" + disponible +
                '}';
    }
}