package com.example.prj.controller.admin;

import com.example.prj.classes.RegisterRequest;
import com.example.prj.dto.StudentDTO;
import com.example.prj.entity.Student;
import com.example.prj.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/students")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminStudentController {
    private final StudentService studentService;

    public AdminStudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getStudent(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudent(id));
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody RegisterRequest student) {
        return ResponseEntity.ok(studentService.addStudent(student));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody StudentDTO student ) {
        return ResponseEntity.ok(studentService.updateStudentS(id, student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }
}