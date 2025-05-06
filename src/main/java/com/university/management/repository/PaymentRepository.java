package com.university.management.repository;

import com.university.management.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    List<Payment> findByStatus(Payment.PaymentStatus status);
    List<Payment> findAllByOrderByDateDesc();
}