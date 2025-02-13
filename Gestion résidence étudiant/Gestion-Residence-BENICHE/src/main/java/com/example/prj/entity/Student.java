package com.example.prj.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Entity
@Table(name = "students")
public class Student  {


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

    @Transient
    private String token; // Token généré lors de l'authentification

    @OneToOne(mappedBy = "student")
    @JsonManagedReference
    private Room room;

    public Student(String password, String email) {
        this.email = email;
        this.password = password;
    }
    public Student(Long id, String username, String password, String firstName, String lastName, String email, LocalDate dateNaissance, String token) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateNaissance = dateNaissance;
        this.token = token;
    }
    public Student( Long id,String username, String password, String firstName, String lastName, String email,  String username1, String password1, String firstName1, String lastName1, String email1, LocalDate dateNaissance) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateNaissance = dateNaissance;
    }

    public Student(String username, String password, String firstName, String lastName, String email,Long id, String username1, String password1, String firstName1, String lastName1, String email1, LocalDate dateNaissance, String token) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateNaissance = dateNaissance;
        this.token = token;
    }


    public Student() { }

    public Student(Long id) {
        this.id = id;
    }

    public Student(Long id, String username, String password, String firstName, String lastName, String email, LocalDate dateNaissance) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateNaissance = dateNaissance;
    }
    public Student(String username, String password, String firstName, String lastName, String email, LocalDate dateNaissance) {
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

    public String getToken() {
        return "user-token-" + id; // Simplification pour l'exemple
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }


}
