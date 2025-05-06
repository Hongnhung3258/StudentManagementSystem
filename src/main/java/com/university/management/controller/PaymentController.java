package com.university.management.controller;

import com.university.management.entity.Payment;
import com.university.management.service.ActivityLogService;
import com.university.management.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;
    private final ActivityLogService activityLogService;

    @Autowired
    public PaymentController(PaymentService paymentService, ActivityLogService activityLogService) {
        this.paymentService = paymentService;
        this.activityLogService = activityLogService;
    }

    @GetMapping
    public String showAllPayments(Model model) {
        List<Payment> payments = paymentService.findAllPayments();
        model.addAttribute("payments", payments);
        model.addAttribute("pageTitle", "Quản lý tài chính");
        model.addAttribute("activeStatus", "all");
        return "payment/list";
    }

    @GetMapping("/status/{status}")
    public String showPaymentsByStatus(@PathVariable("status") String status, Model model) {
        Payment.PaymentStatus paymentStatus;
        
        switch (status.toLowerCase()) {
            case "confirmed":
                paymentStatus = Payment.PaymentStatus.CONFIRMED;
                break;
            case "expired":
                paymentStatus = Payment.PaymentStatus.EXPIRED;
                break;
            case "pending":
                paymentStatus = Payment.PaymentStatus.PENDING;
                break;
            default:
                return "redirect:/payments";
        }
        
        List<Payment> payments = paymentService.findPaymentsByStatus(paymentStatus);
        model.addAttribute("payments", payments);
        model.addAttribute("pageTitle", "Tài chính - " + paymentStatus.getDisplayValue());
        model.addAttribute("activeStatus", status.toLowerCase());
        return "payment/list";
    }

    @PostMapping("/update-status/{id}")
    public String updatePaymentStatus(
            @PathVariable("id") Integer id,
            @RequestParam("status") String status,
            RedirectAttributes redirectAttributes,
            Principal principal) {
        
        try {
            Payment.PaymentStatus paymentStatus;
            switch (status.toLowerCase()) {
                case "confirmed":
                    paymentStatus = Payment.PaymentStatus.CONFIRMED;
                    break;
                case "expired":
                    paymentStatus = Payment.PaymentStatus.EXPIRED;
                    break;
                case "pending":
                    paymentStatus = Payment.PaymentStatus.PENDING;
                    break;
                default:
                    redirectAttributes.addFlashAttribute("errorMessage", "Trạng thái không hợp lệ");
                    return "redirect:/payments";
            }
            
            String username = principal.getName();
            Payment updatedPayment = paymentService.updatePaymentStatus(id, paymentStatus, username);
            
            // Log the activity
            activityLogService.logActivity(
                username, 
                "Cập nhật", 
                "Tài chính", 
                updatedPayment.getId()
            );
            
            redirectAttributes.addFlashAttribute("successMessage", 
                    "Đã cập nhật trạng thái thanh toán thành " + paymentStatus.getDisplayValue());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi: " + e.getMessage());
        }
        
        return "redirect:/payments";
    }
}