package com.university.management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "class")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Class {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "malop", length = 5, nullable = false, unique = true)
    private String classCode;
    
    @Column(name = "tenlop", length = 100, nullable = false)
    private String className;
    
    @Column(name = "tenmon", length = 100)
    private String courseName;
    
    @Column(name = "tenkhoa", length = 60)
    private String departmentName;
    
    @Column(name = "soluongSV")
    private Integer studentCount;
    
    @Column(name = "maGV", length = 6, nullable = false)
    private String lecturerCode;
    
    @Column(name = "namhoc", length = 9, nullable = false)
    private String academicYear;
    
    @Column(name = "hocky", nullable = false)
    private Byte semester;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenmon", referencedColumnName = "tenmon", insertable = false, updatable = false)
    private Course course;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maGV", referencedColumnName = "maGV", insertable = false, updatable = false)
    private Lecturer lecturer;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenkhoa", referencedColumnName = "tenkhoa", insertable = false, updatable = false)
    private Department department;
}
