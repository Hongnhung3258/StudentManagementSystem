package com.university.management.service;

import com.university.management.entity.Payment;
import java.util.List;

public interface PaymentService {
    List<Payment> findAllPayments();
    List<Payment> findPaymentsByStatus(Payment.PaymentStatus status);
    Payment getPaymentById(Integer id);
    Payment savePayment(Payment payment);
    Payment updatePaymentStatus(Integer id, Payment.PaymentStatus status, String approverUsername);
    void deletePayment(Integer id);
}