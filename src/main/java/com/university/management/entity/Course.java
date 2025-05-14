package com.university.management.entity;

import com.university.management.entity.Department;
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

    @Column(name = "course_code", unique = true)
    @Size(max = 7)
    private String courseCode;

    @Column(name = "course_name", nullable = false, unique = true, length = 100)
    @NotBlank(message = "Tên môn học là bắt buộc")
    @Size(max = 100, message = "Tên môn học tối đa 100 ký tự")
    private String courseName;

    @Column(name = "credits", nullable = false)
    @Min(value = 1, message = "Số tín chỉ phải từ 1 đến 10")
    @Max(value = 10, message = "Số tín chỉ phải từ 1 đến 10")
    private Integer credits;

    @Column(name = "department_name", length = 60)
    private String departmentName;
    
    @ManyToOne
    @JoinColumn(name = "department_name", referencedColumnName = "department_name", insertable = false, updatable = false)
    private Department department;

    // Constructors
    public Course() {}

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