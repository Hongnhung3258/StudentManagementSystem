package com.example.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "department")
public class Department {

    @Id
    @Column(name = "department_name", length = 60)
    @NotBlank(message = "Tên khoa là bắt buộc")
    @Size(max = 60, message = "Tên khoa tối đa 60 ký tự")
    private String departmentName;

    @Column(name = "department_code", unique = true, length = 4)
    @Size(max = 4, message = "Mã khoa tối đa 4 ký tự")
    private String departmentCode;

    @Column(name = "head_lecturer_code", length = 6)
    @Size(max = 6, message = "Mã trưởng khoa tối đa 6 ký tự")
    private String headLecturerCode;

    // Constructors
    public Department() {}

    // Getters and Setters
    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getHeadLecturerCode() {
        return headLecturerCode;
    }

    public void setHeadLecturerCode(String headLecturerCode) {
        this.headLecturerCode = headLecturerCode;
    }
}