package com.university.management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "news")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class News {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "matin", length = 8, nullable = false, unique = true)
    private String newsCode;
    
    @Column(name = "tieude", length = 200, nullable = false)
    private String title;
    
    @Column(name = "noidung", nullable = false, columnDefinition = "TEXT")
    private String content;
    
    @Column(name = "tailieu_url", length = 255)
    private String documentUrl;
    
    @Column(name = "ngaytao", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "nguoitao", length = 100)
    private String createdBy;
}
