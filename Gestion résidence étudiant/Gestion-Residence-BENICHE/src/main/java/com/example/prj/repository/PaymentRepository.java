package com.example.prj.repository;

import com.example.prj.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    @Query("SELECT p FROM Payment p WHERE p.student.id = :studentId")
    List<Payment> findByStudentId(@Param("studentId") Long studentId);


    @Query("SELECT p FROM Payment p WHERE p.isPaid = :isPaid")
    List<Payment> findByIsPaid(@Param("isPaid") Boolean isPaid);


    @Query("SELECT COUNT(p) FROM Payment p WHERE p.isPaid = false")
    int countPendingPayments();

    // Calculer le revenu mensuel total
    @Query("SELECT SUM(p.amount) FROM Payment p WHERE MONTH(p.paymentDate) = MONTH(CURRENT_DATE) AND YEAR(p.paymentDate) = YEAR(CURRENT_DATE)")
    double calculateMonthlyRevenue();

    @Query("SELECT p FROM Payment p WHERE p.student.id = :studentId AND p.paymentDate BETWEEN :startDate AND :endDate")
    List<Payment> findByStudentIdAndDateBetween(@Param("studentId") Long studentId, @Param("startDate") String startDate, @Param("endDate") String endDate);


    @Query("SELECT p FROM Payment p WHERE p.room.id = :roomId")
    List<Payment> findByRoomId(@Param("roomId") Long roomId);


    @Query("SELECT p FROM Payment p WHERE p.paymentDate BETWEEN :startDate AND :endDate")
    List<Payment> findByPaymentDateBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);


    Optional<Payment> findFirstByRoom_IdAndStudent_IdOrderByPaymentDateDesc(Long roomId, Long studentId);
}