package com.example.prj.controller.student;

import com.example.prj.entity.Payment;
import com.example.prj.entity.Room;
import com.example.prj.entity.Student;
import com.example.prj.service.PaymentService;
import com.example.prj.service.RoomService;
import com.example.prj.service.StudentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/residents")
public class ResidentsController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private PaymentService paymentService;

    @GetMapping("/me")
    public ResponseEntity<?> getResident(HttpSession session) {
        if(session.getAttribute("student") != null) {
            Student student = (Student) session.getAttribute("student");
            return ResponseEntity.ok(studentService.getStudent(student.getId()));
        } else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You need to be logged in");
        }
    }

    @GetMapping("/me/room")
    public ResponseEntity<?> getResidentRoom(HttpSession session) {
        if(session.getAttribute("student") != null) {
            Student student = (Student) session.getAttribute("student");
            Room room = roomService.getRoomByStudentId(student.getId());
            if (room != null) {
                return ResponseEntity.ok(room);
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You need to be logged in");
        }
    }
    @GetMapping("/me/payments")
    public ResponseEntity<?> getResidentPayment(HttpSession session){
        if(session.getAttribute("student") != null) {
            Student student = (Student) session.getAttribute("student");
            List<Payment> payments = paymentService.getStudentPayments(student.getId());
            if (payments != null) {
                return ResponseEntity.ok(payments);
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You need to be logged in");
        }
    }
    @GetMapping("/me/rooms")
    public ResponseEntity<?> getResidentRooms(HttpSession session) {
        if(session.getAttribute("student") != null) {
            Student student = (Student) session.getAttribute("student");
            List<Room> rooms =  roomService.getRoomsByStudentId(student.getId());
            if (rooms != null) {
                return ResponseEntity.ok(rooms);
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You need to be logged in");
        }
    }
}

