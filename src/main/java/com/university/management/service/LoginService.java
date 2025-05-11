package com.example.service;

import com.example.entity.Login;

/**
 * Giao diện dịch vụ cho các thao tác liên quan đến đăng nhập.
 */
public interface LoginService {

    Login findByUsername(String username);

    void saveLogin(Login login);

    boolean existsByUsername(String username);

    void updatePassword(String username, String newPassword);
}
