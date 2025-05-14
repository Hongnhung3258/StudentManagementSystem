package com.university.management.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "login")
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true, length = 50)
    @NotBlank(message = "Tên đăng nhập là bắt buộc")
    @Size(max = 50, message = "Tên đăng nhập tối đa 50 ký tự")
    private String username;

    @Column(name = "password", nullable = false, length = 100)
    @NotBlank(message = "Mật khẩu là bắt buộc")
    private String password;

    @Column(name = "role", nullable = false, length = 20)
    private String role;
    
    public enum Role {
        Admin, Student, Lecturer
    }

    // Constructors
    public Login() {}

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
     public String getRole() {
        return role;
    }

    public void setId(String role) {
        this.role = role;
    }
}