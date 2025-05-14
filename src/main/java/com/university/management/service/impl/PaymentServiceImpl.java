package com.university.management.service.impl;

import com.example.entity.Payment;
import com.example.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Page<Payment> findPaymentsByStatus(String status, Pageable pageable) {
        Payment.Status paymentStatus = null;
        if (status != null && !status.equals("all")) {
            try {
                paymentStatus = Payment.Status.valueOf(status.toUpperCase());
            } catch (IllegalArgumentException e) {
                // Trả về trang rỗng nếu trạng thái không hợp lệ
                return Page.empty(pageable);
            }
        }
        return paymentRepository.findByStatus(paymentStatus, pageable);
    }

    @Override
    @Transactional
    public void updatePaymentStatus(Integer id, Payment.Status status, String approverUsername) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Thanh toán không tồn tại"));
        if (payment.getStatus() != Payment.Status.PENDING) {
            throw new IllegalStateException("Chỉ có thể cập nhật trạng thái cho thanh toán đang chờ");
        }
        payment.setStatus(status);
        payment.setApprovedBy(approverUsername);
        payment.setApprovalDate(LocalDateTime.now());
        paymentRepository.save(payment);
    }
}