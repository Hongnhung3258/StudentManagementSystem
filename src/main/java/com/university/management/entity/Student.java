package com.university.management.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * Entity mapping to the student table in the database.
 */
@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "intake", length = 2)
    @Size(max = 2, min = 2, message = "Khóa có 2 ký tự")
    private String intake;

    @Column(name = "student_id", nullable = false, unique = true)
    private String studentId;

    @Column(name = "department_name", length = 60)
    private String departmentName;

    @Column(name = "full_name", nullable = false, length = 50)
    @NotBlank(message = "Họ tên là bắt buộc")
    @Size(max = 50, message = "Họ tên tối đa 50 ký tự")
    private String fullName;

    @Column(name = "gender", nullable = false)
    @NotNull(message = "Giới tính là bắt buộc")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "date_of_birth", nullable = false)
    @NotNull(message = "Ngày sinh là bắt buộc")
    private LocalDate dateOfBirth;

    @Column(name = "phone_number", unique = true, length = 15)
    @Size(max = 15, message = "Số điện thoại tối đa 15 ký tự")
    private String phoneNumber;

    @Column(name = "address", length = 100)
    @Size(max = 100, message = "Địa chỉ tối đa 100 ký tự")
    private String address;

    @Column(name = "national_id", nullable = false, unique = true, length = 12)
    @NotBlank(message = "Số CCCD là bắt buộc")
    @Size(min = 12, max = 12, message = "Số CCCD phải có 12 chữ số")
    private String nationalId;

    @Column(name = "password", nullable = false, length = 8)
    private String password;

    @Column(name = "email", unique = true, length = 50, insertable = false, updatable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "department_name", referencedColumnName = "department_name", insertable = false, updatable = false)
    private Department department;

    @ManyToOne
    @JoinColumn(name = "email", referencedColumnName = "username", insertable = false, updatable = false)
    private Login login;

    public enum Gender {
        Nam, Nữ
    }

    // Constructors
    public Student() {}

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIntake() {
        return intake;
    }

    public void setIntake(String intake) {
        this.intake = intake;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setgetNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}