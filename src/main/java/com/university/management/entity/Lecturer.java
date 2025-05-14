package com.university.management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "lecturers")
public class Lecturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 6)
    private String lecturerId;

    @NotBlank(message = "Họ và tên không được để trống")
    @Size(max = 50, message = "Họ và tên tối đa 50 ký tự")
    @Column(length = 50, nullable = false)
    private String fullName;

    @NotBlank(message = "Giới tính không được để trống")
    @Column(length = 10, nullable = false)
    private String gender;

    @NotNull(message = "Ngày sinh không được để trống")
    @Past(message = "Ngày sinh phải là ngày trong quá khứ")
    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "\\d{10,15}", message = "Số điện thoại phải có 10-15 chữ số")
    @Column(length = 15, nullable = false, unique = true)
    private String phoneNumber;

    @NotBlank(message = "Địa chỉ không được để trống")
    @Size(max = 100, message = "Địa chỉ tối đa 100 ký tự")
    @Column(length = 100, nullable = false)
    private String address;

    @NotBlank(message = "CMND/CCCD không được để trống")
    @Pattern(regexp = "\\d{12}", message = "CMND/CCCD phải có 12 chữ số")
    @Column(length = 12, nullable = false, unique = true)
    private String nationalId;

    @NotBlank(message = "Khoa không được để trống")
    @Size(max = 100, message = "Tên khoa tối đa 100 ký tự")
    @Column(length = 100, nullable = false)
    private String departmentName;

    @NotBlank(message = "Học vị không được để trống")
    @Column(length = 50, nullable = false)
    private String academicDegree;

    @NotBlank(message = "Chức vụ không được để trống")
    @Column(length = 50, nullable = false)
    private String position;

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(String lecturerId) {
        this.lecturerId = lecturerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
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

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getAcademicDegree() {
        return academicDegree;
    }

    public void setAcademicDegree(String academicDegree) {
        this.academicDegree = academicDegree;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}