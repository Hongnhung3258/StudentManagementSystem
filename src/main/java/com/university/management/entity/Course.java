package com.university.management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "course")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "mamon", length = 5, nullable = false, unique = true)
    private String courseCode;
    
    @Column(name = "tenmon", length = 100, nullable = false, unique = true)
    private String courseName;
    
    @Column(name = "tinchi", nullable = false)
    private Byte credits;
    
    @Column(name = "tenkhoa", length = 60)
    private String departmentName;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenkhoa", referencedColumnName = "tenkhoa", insertable = false, updatable = false)
    private Department department;
}
