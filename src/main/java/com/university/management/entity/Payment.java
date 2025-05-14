package com.university.management.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
public class Payment {

    @Entity
@Table(name = "payment")
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_id", nullable = false, length = 6)
    private String studentId;

    @Column(name = "payment_date", nullable = false)
    private LocalDate paymentDate;

    @Column(name = "amount", nullable = false)
    private Decimal amount;

    @Column(name = "description", nullable = false, length = 200)
    private String description;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status = Status.Đang_chờ;

    @Column(name = "approved_by", length = 40)
    private String approvedBy;

    @Column(name = "approval_date")
    private LocalDateTime approvalDate;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "student_id", insertable = false, updatable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "approved_by", referencedColumnName = "username", insertable = false, updatable = false)
    private Login approvedByLogin;

    public enum Status {
        Xác_nhận, Đang_chờ, Hết_hạn
    }

    // Constructors
    public Payment() {}

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudent() {
        return studentId;
    }

    public void setStudent(String studentId) {
        this.studentId = studentId;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Decimal getAmount() {
        return amount;
    }

    public void setAmount(Decimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public LocalDateTime getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(LocalDateTime approvalDate) {
        this.approvalDate = approvalDate;
    }
   }
}