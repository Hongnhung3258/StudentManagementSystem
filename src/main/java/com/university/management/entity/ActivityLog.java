package com.university.management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "activity_log")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityLog {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "tendangnhap", nullable = false, length = 40)
    private String username;
    
    @Column(name = "hoatdong", nullable = false, length = 20)
    private String action;
    
    @Column(name = "muctieu", nullable = false, length = 50)
    private String target;
    
    @Column(name = "id_muctieu", nullable = false)
    private Integer targetId;
    
    @Column(name = "ngaygio", nullable = false)
    private LocalDateTime timestamp = LocalDateTime.now();
}