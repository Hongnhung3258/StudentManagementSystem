package com.university.management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "lecturer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lecturer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "maGV", length = 6, nullable = false, unique = true)
    private String lecturerCode;
    
    @Column(name = "tenkhoa", length = 60)
    private String departmentName;
    
    @Column(name = "hoten", length = 50, nullable = false)
    private String fullName;
    
    @Column(name = "gioitinh", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;
    
    @Column(name = "ngaysinh", nullable = false)
    private LocalDate dateOfBirth;
    
    @Column(name = "sdt", length = 15, unique = true)
    private String phoneNumber;
    
    @Column(name = "diachi", length = 100)
    private String address;
    
    @Column(name = "cccd", length = 12, nullable = false, unique = true)
    private String idNumber;
    
    @Column(name = "hocvi", length = 50)
    private String academicDegree;
    
    @Column(name = "chucvu", length = 50)
    private String position;
    
    @Column(name = "matkhau", length = 8, nullable = false)
    private String password;
    
    @Column(name = "email", length = 50, unique = true, insertable = false, updatable = false)
    private String email;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenkhoa", referencedColumnName = "tenkhoa", insertable = false, updatable = false)
    private Department department;
    
    public enum Gender {
        Nam, Nữ
    }
}
