package com.example.prj.service;

import com.example.prj.classes.RegisterRequest;
import com.example.prj.dto.StudentDTO;
import com.example.prj.entity.Student;
import com.example.prj.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Retrieve all students
    public List<Student> getAllStudents() {
        List<Student> result = studentRepository.findAll();
        return result;
    }

    // Retrieve a single student by ID
    public Student getStudent(Long id) {
       Optional<Student> student = Optional.of(studentRepository.findByUserId((id)));
        return student.orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));
    }

    // Create a new student
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    // Update an existing student
    public Student updateStudent(Long id, Student studentDetails) {
        Student existingStudent = (Student) getStudent(id);
        existingStudent.setUsername(studentDetails.getUsername());
        existingStudent.setLastName(studentDetails.getLastName());
        existingStudent.setFirstName(studentDetails.getFirstName());
        existingStudent.setDateNaissance(studentDetails.getDateNaissance());
        existingStudent.setEmail(studentDetails.getEmail());
        existingStudent.setPassword(studentDetails.getPassword());
        // Add other fields as needed
        return studentRepository.save(existingStudent);
    }

    // Delete a student
    public void deleteStudent(Long id) {
        Student student = (Student) getStudent(id);
        studentRepository.delete(student);
    }

    public List<Object[]> getAllStudentsWithUserInfo() {
        List<Student> students = studentRepository.findAll();

        return students.stream()
                .map(student -> {
                    Student user = studentRepository.findByUserId(student.getId());
                    if (user == null) {
                        return new Object[]{ student };
                    }
                    return new Object[]{user, student};
                })
                .collect(Collectors.toList());
    }

    public Student addStudent(RegisterRequest registerRequest) {
        Student student = new Student(
                registerRequest.getUsername(),
                registerRequest.getPassword(),
                registerRequest.getFirstName(),
                registerRequest.getLastName(),
                registerRequest.getEmail(),
                registerRequest.getDateNaissance()
        );

        return studentRepository.save(student);
    }

    public Student updateStudentS(Long id, StudentDTO student) {
        Optional<Student> existingStudentOptional = studentRepository.findById(id);

        if (existingStudentOptional.isPresent()) {
            Student existingStudent = existingStudentOptional.get();
            existingStudent.setFirstName(student.getFirstName());
            existingStudent.setLastName(student.getLastName());
            existingStudent.setUsername(student.getUsername());
            existingStudent.setEmail(student.getEmail());
            existingStudent.setPassword(student.getPassword());
            existingStudent.setDateNaissance(student.getDateNaissance());
            return studentRepository.save(existingStudent);
        } else {
            throw new RuntimeException("Student with ID " + id + " not found!");
        }
    }
}
