package com.example.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Entity ánh xạ với bảng login trong cơ sở dữ liệu.
 * Bảng login lưu thông tin đăng nhập của người dùng.
 */
@Entity
@Table(name = "login")
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", nullable = false, unique = true, length = 40)
    @NotBlank(message = "Tên đăng nhập là bắt buộc")
    @Size(max = 40, message = "Tên đăng nhập tối đa 40 ký tự")
    private String username;

    @Column(name = "password", nullable = false, length = 100)
    @NotBlank(message = "Mật khẩu là bắt buộc")
    @Size(max = 100, message = "Mật khẩu tối đa 100 ký tự")
    private String password;

    // Constructors
    public Login() {}

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
}