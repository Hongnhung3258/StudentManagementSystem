package com.university.management.service.impl;

import com.university.management.entity.Payment;
import com.university.management.repository.PaymentRepository;
import com.university.management.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<Payment> findAllPayments() {
        return paymentRepository.findAllByOrderByDateDesc();
    }

    @Override
    public List<Payment> findPaymentsByStatus(Payment.PaymentStatus status) {
        return paymentRepository.findByStatus(status);
    }

    @Override
    public Payment getPaymentById(Integer id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy thanh toán với ID: " + id));
    }

    @Override
    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Payment updatePaymentStatus(Integer id, Payment.PaymentStatus status, String approverUsername) {
        Payment payment = getPaymentById(id);
        payment.setStatus(status);
        payment.setApproverUsername(approverUsername);
        payment.setApprovalDate(LocalDateTime.now());
        return paymentRepository.save(payment);
    }

    @Override
    public void deletePayment(Integer id) {
        paymentRepository.deleteById(id);
    }
}