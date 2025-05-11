package com.example.controller;

import com.example.entity.Payment;
import com.example.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/payments")
    public String listPayments(
            @RequestParam(value = "status", required = false, defaultValue = "all") String status,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Payment> payments = paymentService.findPaymentsByStatus(status, pageable);
        model.addAttribute("payments", payments);
        model.addAttribute("activeStatus", status);
        model.addAttribute("pageTitle", "Quản lý tài chính");
        return "payments";
    }

    @PostMapping("/payments/update-status/{id}")
    public String updatePaymentStatus(
            @PathVariable Integer id,
            @RequestParam("status") String status,
            Model model) {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            Payment.Status paymentStatus = Payment.Status.valueOf(status.toUpperCase());
            paymentService.updatePaymentStatus(id, paymentStatus, username);
            model.addAttribute("successMessage", "Cập nhật trạng thái thanh toán thành công");
        } catch (IllegalArgumentException | IllegalStateException e) {
            model.addAttribute("errorMessage", "Lỗi: " + e.getMessage());
        }
        return "redirect:/payments";
    }
}