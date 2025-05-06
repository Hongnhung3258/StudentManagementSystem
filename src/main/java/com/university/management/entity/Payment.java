package com.university.management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "maSV", nullable = false, length = 6)
    private String studentId;
    
    @Column(name = "hoten", nullable = false, length = 50)
    private String fullName;
    
    @Column(name = "ngay", nullable = false)
    private LocalDate date;
    
    @Column(name = "sotien", nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;
    
    @Column(name = "mota", nullable = false, length = 200)
    private String description;
    
    @Column(name = "trangthai", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private PaymentStatus status = PaymentStatus.PENDING;
    
    @Column(name = "nguoiduyet", length = 40)
    private String approverUsername;
    
    @Column(name = "ngaypheduyet", nullable = false)
    private LocalDateTime approvalDate = LocalDateTime.now();
    
    public enum PaymentStatus {
        CONFIRMED("Xác nhận"),
        EXPIRED("Hết hạn"),
        PENDING("Đang chờ");
        
        private final String displayValue;
        
        PaymentStatus(String displayValue) {
            this.displayValue = displayValue;
        }
        
        public String getDisplayValue() {
            return displayValue;
        }
    }
}