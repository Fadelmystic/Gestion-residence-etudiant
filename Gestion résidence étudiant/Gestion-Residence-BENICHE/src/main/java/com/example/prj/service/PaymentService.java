package com.example.prj.service;

import com.example.prj.dto.PaymentRecord;
import com.example.prj.entity.PayementStatus;
import com.example.prj.entity.Payment;
import com.example.prj.entity.Room;
import com.example.prj.entity.Student;
import com.example.prj.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final RoomService roomService;
    private final StudentService studentService;

    public PaymentService(PaymentRepository paymentRepository, RoomService roomService, StudentService studentService) {
        this.paymentRepository = paymentRepository;
        this.roomService = roomService;
        this.studentService = studentService;
    }

    // Enregistrer un paiement
    public Payment recordPayment(Payment payment) {
        return paymentRepository.save(payment);
    }


    // Mettre à jour un paiement existant
    public Payment updatePayment(Long id, Payment payment) {
        Payment existing = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        existing.setAmount(payment.getAmount());
        existing.setPaymentMethod(payment.getPaymentMethod());
        existing.setPaid(payment.isPaid());

        return paymentRepository.save(existing);
    }

    // Récupérer les paiements d'un étudiant par ID
    public List<Payment> getStudentPayments(Long studentId) {
        return paymentRepository.findByStudentId(studentId);
    }

    // Récupérer les paiements impayés
    public List<Payment> getUnpaidPayments() {
        return paymentRepository.findByIsPaid(false);
    }

    // Supprimer un paiement par ID
    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

    // Récupérer un paiement par son ID
    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with ID: " + id));
    }

    // Récupérer les paiements dans une plage de dates
    public List<Payment> getPaymentsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return paymentRepository.findByPaymentDateBetween(startDate, endDate);
    }

    // Calculer le total des paiements d'un étudiant
    public BigDecimal getTotalPayments(Long studentId) {
        List<Payment> payments = paymentRepository.findByStudentId(studentId);
        return payments.stream()
                .map(Payment::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Récupérer les paiements associés à une chambre (si applicable)
    public List<Payment> getPaymentsByRoomId(Long roomId) {
        return paymentRepository.findByRoomId(roomId);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment createPayment(Long studentId, Long roomId, String paymentMethod, BigDecimal amount) {
        Student student = studentService.getStudent(studentId);
        Room room = roomService.getRoomById(roomId);
        Payment payment = new Payment();
        payment.setStudent(student);
        payment.setRoom(room);
        payment.setAmount(amount);
        payment.setPaymentDate(LocalDateTime.now());
        payment.setPaymentMethod(paymentMethod);
        payment.setReceiptNumber(UUID.randomUUID().toString());
        payment.setPaid(false);
        payment.setDueDate(LocalDateTime.now().plusDays(7));
        payment.setStatus(PayementStatus.PENDING);
        roomService.selectRoom(roomId, studentId);
        return paymentRepository.save(payment);

    }
    public Payment recordPaymentP(PaymentRecord paymentDTO){
        Student student = paymentDTO.getStudentId() != null ? studentService.getStudent(paymentDTO.getStudentId()) : null;
        Room room = paymentDTO.getRoomId() != null ?  roomService.getRoomById(paymentDTO.getRoomId()) : null;
        Payment payment = new Payment(
                student,
                room,
                paymentDTO.getAmount(),
                LocalDateTime.now(),
                paymentDTO.getPaymentMethod(),
                "12345",
                true,
                LocalDateTime.now(),
                paymentDTO.getStatus()

        );
        if(room != null && room.isAvailable()){
            room.setAvailable(false);
            roomService.updateRoomR(room.getId(),room);
        }

        return paymentRepository.save(payment);
    }
    public Payment updatePaymentP(Long id, PaymentRecord paymentDTO) {
        Optional<Payment> existingPaymentOptional = paymentRepository.findById(id);
        if(existingPaymentOptional.isPresent()) {
            Payment existingPayment= existingPaymentOptional.get();
            existingPayment.setAmount(paymentDTO.getAmount());
            existingPayment.setPaymentMethod(paymentDTO.getPaymentMethod());
            existingPayment.setStatus(paymentDTO.getStatus());
            if(paymentDTO.getRoomId() != null){
                Room room = roomService.getRoomById(paymentDTO.getRoomId());
                if (room.isAvailable()) {
                    room.setAvailable(false);
                    roomService.updateRoomR(room.getId(),room);
                }
                existingPayment.setRoom(room);
            } else {
                existingPayment.setRoom(null);
            }
            if(paymentDTO.getStudentId() != null){
                Student student = studentService.getStudent(paymentDTO.getStudentId());
                existingPayment.setStudent(student);
            } else {
                existingPayment.setStudent(null);
            }


            return paymentRepository.save(existingPayment);
        } else {
            throw new RuntimeException("Payment with ID " + id + " not found!");
        }
    }


    public Payment getPaymentByRoomId(Long roomId, Long studentId){
        return paymentRepository.findFirstByRoom_IdAndStudent_IdOrderByPaymentDateDesc(roomId, studentId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "payment not found"));
    }
}