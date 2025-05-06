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
    private Integer stt;
    
    @Column(name = "tendangnhap", nullable = false, length = 40)
    private String username;
    
    @Column(name = "matkhau", nullable = false, length = 30)
    private String password;
}
