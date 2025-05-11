package com.example.entity;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "class")
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "class_code", nullable = false, unique = true, length = 5)
    @Size(max = 5, message = "Mã lớp tối đa 5 chữ số")
    @Pattern(regexp = "[0-9]{0,5}", message = "Mã lớp chỉ chứa chữ số")
    private String classCode;

    @Column(name = "class_name", nullable = false, length = 100)
    @NotBlank(message = "Tên lớp là bắt buộc")
    @Size(max = 100, message = "Tên lớp tối đa 100 ký tự")
    private String className;

    @Column(name = "course_name", length = 100)
    private String courseName;

    @Column(name = "department_name", length = 60)
    private String departmentName;

    @Column(name = "student_count", nullable = false)
    @Min(value = 0, message = "Số lượng sinh viên phải lớn hơn hoặc bằng 0")
    @Max(value = 500, message = "Số lượng sinh viên tối đa 500")
    private Integer studentCount = 0;

    @Column(name = "lecturer_id", nullable = false, length = 6)
    @NotBlank(message = "Mã giảng viên là bắt buộc")
    @Size(max = 6, message = "Mã giảng viên tối đa 6 ký tự")
    private String lecturerId;

    @Column(name = "academic_year", nullable = false, length = 9)
    @NotBlank(message = "Năm học là bắt buộc")
    @Pattern(regexp = "^[0-9]{4}-[0-9]{4}$", message = "Năm học phải có định dạng YYYY-YYYY")
    private String academicYear;

    @Column(name = "semester", nullable = false)
    @Min(value = 1, message = "Học kỳ phải từ 1 đến 4")
    @Max(value = 4, message = "Học kỳ phải từ 1 đến 4")
    private Integer semester;

    // Constructors
    public Class() {}

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(Integer studentCount) {
        this.studentCount = studentCount;
    }

    public String getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(String lecturerId) {
        this.lecturerId = lecturerId;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }
}