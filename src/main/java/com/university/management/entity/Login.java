package com.university.management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "login")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Login {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stt")
    private Integer id;
    
    @Column(name = "tendangnhap", length = 40, nullable = false)
    private String username;
    
    @Column(name = "matkhau", length = 100, nullable = false)
    private String password;
}