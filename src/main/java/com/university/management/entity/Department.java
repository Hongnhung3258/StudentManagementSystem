package com.university.management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "department")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "makhoa", length = 4, nullable = false, unique = true)
    private String departmentCode;
    
    @Column(name = "tenkhoa", length = 60, nullable = false, unique = true)
    private String departmentName;
    
    @Column(name = "matruongkhoa", length = 6)
    private String headLecturerCode;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matruongkhoa", referencedColumnName = "maGV", insertable = false, updatable = false)
    private Lecturer headLecturer;
}
