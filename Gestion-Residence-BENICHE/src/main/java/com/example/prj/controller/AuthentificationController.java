package com.example.prj.controller;


import com.example.prj.classes.LoginRequest;
import com.example.prj.classes.RegisterRequest;
import com.example.prj.entity.Student;
import com.example.prj.repository.StudentRepository;
import com.example.prj.service.StudentService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jdk.jfr.Registered;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/auth")
public class AuthentificationController {


    private final StudentRepository studentRepository;
    private final StudentService studentService;

    public AuthentificationController(StudentRepository studentRepository, StudentService studentService) {
        this.studentRepository = studentRepository;
        this.studentService = studentService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid RegisterRequest registerRequest, HttpSession session) {
        try {
            Student student = studentService.addStudent(registerRequest);
            session.setAttribute("student", student); // Mettre l'étudiant dans la session
            return ResponseEntity.status(HttpStatus.CREATED).body(student); // Retourner l'étudiant
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de l'inscription : " + e.getMessage());
        }
    }

    // Méthode pour la connexion de l'utilisateur
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody @Valid LoginRequest loginRequest, HttpSession session) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Student student = studentRepository.findByEmailAndPassword(email, password);
        if (student != null) {
            session.setAttribute("student", student);
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Identifiants incorrects");
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(HttpSession session){
        if (session.getAttribute("student") != null){
            return ResponseEntity.ok(session.getAttribute("student"));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You need to be logged in");
        }
    }

    // Vérification de la session
    @GetMapping("/check-session")
    public ResponseEntity<?> checkSession(HttpSession session) {
        Student student = (Student) session.getAttribute("student");
        if (student != null) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Non authentifié");
        }
    }

    // Déconnexion de l'utilisateur
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate(); // Invalider la session pour déconnecter l'utilisateur
        return ResponseEntity.ok("Déconnexion réussie");
    }




}

