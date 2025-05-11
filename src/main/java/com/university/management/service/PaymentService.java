package com.example.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PaymentService {
    Page<Payment> findPaymentsByStatus(String status, Pageable pageable);
    void updatePaymentStatus(Integer id, Payment.Status status, String approverUsername);
}