package com.example.prj.controller.student;

import com.example.prj.dto.PaymentDTO;
import com.example.prj.entity.Room;
import com.example.prj.entity.Payment;
import com.example.prj.entity.Maintenance;
import com.example.prj.entity.Student;
import com.example.prj.service.PaymentReceiptService;
import com.example.prj.service.PaymentService;
import com.example.prj.service.RoomService;
import com.example.prj.service.StudentDashboardService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/student")

@CrossOrigin(origins = "http://localhost:3000")
public class StudentDashboardController {
    @Autowired
    private RoomService roomService;

    @Autowired
    private PaymentService paymentService;
    @Autowired
    private PaymentReceiptService paymentReceiptService;

    private final StudentDashboardService studentDashboardService;

    public StudentDashboardController(StudentDashboardService studentDashboardService) {
        this.studentDashboardService = studentDashboardService;
    }

    @GetMapping("/available-rooms")
    public ResponseEntity<List<Room>> getAvailableRooms() {
        return ResponseEntity.ok(studentDashboardService.getAvailableRooms());
    }

    @PostMapping("/select-room/{roomId}")
    public ResponseEntity<Room> selectRoom(@PathVariable Long roomId, @RequestParam Long studentId) {
        return ResponseEntity.ok(studentDashboardService.selectRoom(roomId, studentId));
    }

    @GetMapping("/payments/{studentId}")
    public ResponseEntity<List<Payment>> getPaymentHistory(@PathVariable Long studentId) {
        return ResponseEntity.ok(studentDashboardService.getPaymentHistory(studentId));
    }

    @PostMapping("/report-incident")
    public ResponseEntity<Maintenance> reportIncident(@RequestBody Maintenance incident) {
        return ResponseEntity.ok(studentDashboardService.reportIncident(incident));
    }

    @GetMapping("/room/{roomId}")
    public ResponseEntity<?> getRoom(@PathVariable Long roomId, HttpSession session) {
        if(session.getAttribute("student") != null) {
            return ResponseEntity.ok(roomService.getRoomById(roomId));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You need to be logged in");
        }
    }

    @PostMapping("/process-reservation/{roomId}")
    public ResponseEntity<?> processReservation(@PathVariable Long roomId, HttpSession session, @RequestBody PaymentDTO paymentDTO) {
        if(session.getAttribute("student") != null) {
            Student student = (Student) session.getAttribute("student");
            try{
                Payment payment = paymentService.createPayment(student.getId(), roomId, paymentDTO.getPaymentMethod(), BigDecimal.valueOf(paymentDTO.getAmount()));
//                roomService.UpdateRoomP(roomId,student.getId());
                return ResponseEntity.ok(payment);
            }
            catch (ResponseStatusException e){
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("message", e.getReason());
                return ResponseEntity.status(e.getStatusCode()).body(errorResponse);
            }
            catch(Exception e) {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("message", "An unexpected error occurred while processing the reservation: "+e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
            }
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "You need to be logged in to make a reservation");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }
    @GetMapping("/generate-receipt/{roomId}")
    public ResponseEntity<?> generateReceipt(@PathVariable Long roomId, HttpSession session) {
        if(session.getAttribute("student") != null) {
            Student student = (Student) session.getAttribute("student");
            try{
                Payment payment = paymentService.getPaymentByRoomId(roomId, student.getId());
                byte[] pdfBytes = paymentReceiptService.generateReceiptPdf(
                        student.getId(),
                        student.getFirstName() +" "+ student.getLastName(),
                        roomId,
                        payment.getRoom().getRoomNumber(),
                        payment.getAmount(),
                        payment.getPaymentMethod(),
                        payment.getPaymentDate(),
                        UUID.fromString(payment.getReceiptNumber())

                );
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_PDF);
                headers.setContentDispositionFormData("attachment", "receipt.pdf");

                return ResponseEntity.ok().headers(headers).body(pdfBytes);
            }
            catch (ResponseStatusException e){
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("message", e.getReason());
                return ResponseEntity.status(e.getStatusCode()).body(errorResponse);
            }
            catch (Exception e) {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("message", "An unexpected error occurred while generating the receipt: "+e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
            }
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "You need to be logged in to make a reservation");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }
}