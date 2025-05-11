package com.example.entity;

import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * Entity mapping to the course table in the database.
 */
@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "course_code", unique = true, nullable = false, length = 5)
    @NotBlank(message = "Mã môn là bắt buộc")
    @Size(min = 5, max = 5, message = "Mã môn phải có 5 ký tự")
    private String courseCode;

    @Column(name = "course_name", unique = true, nullable = false, length = 100)
    @NotBlank(message = "Tên môn là bắt buộc")
    @Size(max = 100, message = "Tên môn tối đa 100 ký tự")
    private String courseName;

    @Column(name = "credits", nullable = false)
    @Min(value = 1, message = "Số tín chỉ phải từ 1 đến 10")
    @Max(value = 10, message = "Số tín chỉ phải từ 1 đến 10")
    private Integer credits;

    @Column(name = "department_name", length = 60)
    private String departmentName;

    // Constructors
    public Course() {}

    public Course(String courseCode, String courseName, Integer credits, String departmentName) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.credits = credits;
        this.departmentName = departmentName;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}