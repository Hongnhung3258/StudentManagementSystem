package com.university.management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "schedule")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "malop", nullable = false, length = 5)
    private String classId;
    
    @Column(name = "magv", nullable = false, length = 6)
    private String lecturerId;
    
    @Column(name = "phonghoc", nullable = false, length = 20)
    private String classroom;
    
    @Column(name = "tietbd", nullable = false)
    private Integer startPeriod;
    
    @Column(name = "tietkt", nullable = false)
    private Integer endPeriod;
    
    @Column(name = "thu", nullable = false, length = 20)
    private String dayOfWeek;
    
    @Column(name = "ngaybd", nullable = false)
    private LocalDate startDate;
    
    @Column(name = "namhoc", nullable = false, length = 9)
    private String academicYear;
    
    @Column(name = "hocky", nullable = false)
    private Integer semester;
    
    @Column(name = "lichhoc", length = 50)
    private String scheduleDisplay;
}