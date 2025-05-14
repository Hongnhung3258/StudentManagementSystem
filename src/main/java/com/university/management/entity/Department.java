package com.university.management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "department_code", unique = true, nullable = false)
    @Pattern(regexp = "^KH\\d{2}$|^$", message = "Mã khoa phải có định dạng KH + 2 số (ví dụ: KH01) hoặc để trống")
    private String departmentCode;

    @Column(name = "department_name", nullable = false)
    @NotBlank(message = "Tên khoa không được để trống")
    @Size(max = 100, message = "Tên khoa không được vượt quá 100 ký tự")
    private String departmentName;

    @Column(name = "head_lecturer_code")
    @Size(max = 6, message = "Mã trưởng khoa không được vượt quá 6 ký tự")
    private String headLecturerCode;

    // Constructors
    public Department() {}

    public Department(String departmentCode, String departmentName, String headLecturerCode) {
        this.departmentCode = departmentCode;
        this.departmentName = departmentName;
        this.headLecturerCode = headLecturerCode;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getHeadLecturerCode() {
        return headLecturerCode;
    }

    public void setHeadLecturerCode(String headLecturerCode) {
        this.headLecturerCode = headLecturerCode;
    }
}