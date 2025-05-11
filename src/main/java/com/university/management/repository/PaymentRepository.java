package com.example.repository;

import com.example.entity.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    @Query("SELECT p FROM Payment p WHERE :status IS NULL OR p.status = :status")
    Page<Payment> findByStatus(@Param("status") Payment.Status status, Pageable pageable);
}