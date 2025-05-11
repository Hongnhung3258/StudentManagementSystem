package com.example.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Entity
@Table(name = "lecturer")
public class Lecturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "lecturer_id", unique = true, length = 6)
    @NotBlank(message = "Mã giảng viên là bắt buộc")
    @Size(min = 6, max = 6, message = "Mã giảng viên phải có 6 ký tự")
    private String lecturerCode;

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

    @Column(name = "degree", length = 50)
    @Enumerated(EnumType.STRING)
    private Degree academicDegree;

    @Column(name = "position", length = 50)
    @Enumerated(EnumType.STRING)
    private Position position;

    @Column(name = "password", nullable = false, length = 8)
    @Size(min = 8, max = 8, message = "Mật khẩu phải có 8 ký tự")
    private String password;

    @Column(name = "email", unique = true, length = 50)
    private String email;

    public enum Gender {
        Nam, Nữ
    }

    public enum Degree {
        Cử_nhân, Thạc_sĩ, Tiến_sĩ, PGS_TS, GS_TS, GS_TS_BS, PGS_TS_BS, TS_BS
    }

    public enum Position {
        Trưởng_khoa, Giảng_viên, Trợ_giảng, Phó_trưởng_khoa
    }

    // Constructors
    public Lecturer() {}

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLecturerCode() {
        return lecturerCode;
    }

    public void setLecturerCode(String lecturerCode) {
        this.lecturerCode = lecturerCode;
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

    public Degree getAcademicDegree() {
        return academicDegree;
    }

    public void setAcademicDegree(Degree academicDegree) {
        this.academicDegree = academicDegree;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
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