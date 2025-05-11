package com.example.entity;

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
    private Integer id;

    @Column(name = "intake", length = 2)
    @NotBlank(message = "Mã năm nhập học là bắt buộc")
    @Size(min = 2, max = 2, message = "Mã năm nhập học phải có 2 chữ số")
    private String yearCode;

    @Column(name = "student_id", unique = true, nullable = false, length = 6)
    @NotBlank(message = "Mã sinh viên là bắt buộc")
    @Size(min = 6, max = 6, message = "Mã sinh viên phải có 6 ký tự")
    private String studentCode;

    @Column(name = "department_name", length = 60)
    private String departmentName;

    @Column(name = "full_name", nullable = false, length = 50)
    @NotBlank(message = "Họ tên là bắt buộc")
    @Size(max = 50, message = "Họ tên tối đa 50 ký tự")
    private String fullName;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "date_of_birth", nullable = false)
    @Past(message = "Ngày sinh phải là quá khứ")
    private LocalDate dateOfBirth;

    @Column(name = "phone_number", unique = true, length = 15)
    @Size(max = 15, message = "Số điện thoại tối đa 15 ký tự")
    private String phoneNumber;

    @Column(name = "address", length = 100)
    @Size(max = 100, message = "Địa chỉ tối đa 100 ký tự")
    private String address;

    @Column(name = "national_id", unique = true, nullable = false, length = 12)
    @NotBlank(message = "CMND/CCCD là bắt buộc")
    @Size(min = 12, max = 12, message = "CMND/CCCD phải có 12 ký tự")
    private String idNumber;

    @Column(name = "password", nullable = false, length = 8)
    @Size(min = 8, max = 8, message = "Mật khẩu phải có 8 ký tự")
    private String password;

    @Column(name = "email", unique = true, length = 50)
    private String email;

    public enum Gender {
        Nam, Nữ
    }

    // Constructors
    public Student() {}

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getYearCode() {
        return yearCode;
    }

    public void setYearCode(String yearCode) {
        this.yearCode = yearCode;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
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

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
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