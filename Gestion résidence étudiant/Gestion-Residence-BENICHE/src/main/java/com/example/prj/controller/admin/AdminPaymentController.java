package com.example.prj.controller.admin;

import com.example.prj.dto.PaymentRecord;
import com.example.prj.entity.Payment;
import com.example.prj.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/payments")

@CrossOrigin(origins = "http://localhost:3000")
public class AdminPaymentController {
    private final PaymentService paymentService;

    public AdminPaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Payment>> getStudentPayments(@PathVariable Long studentId) {
        return ResponseEntity.ok(paymentService.getStudentPayments(studentId));
    }

    @GetMapping
    public ResponseEntity<List<Payment>> getPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    @GetMapping("/unpaid")
    public ResponseEntity<List<Payment>> getUnpaidPayments() {
        return ResponseEntity.ok(paymentService.getUnpaidPayments());
    }

    @PostMapping
    public ResponseEntity<Payment> recordPayment(@RequestBody PaymentRecord paymentRecord) {
        return ResponseEntity.ok(paymentService.recordPaymentP(paymentRecord));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Payment> updatePayment(@PathVariable Long id, @RequestBody PaymentRecord paymentRecord) {
        return ResponseEntity.ok(paymentService.updatePaymentP(id, paymentRecord));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return ResponseEntity.ok().build();
    }
}