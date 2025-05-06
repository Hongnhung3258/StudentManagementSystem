package com.university.management.service;

import com.university.management.entity.Login;

public interface LoginService {
    boolean authenticate(String username, String password);
    Login findByUsername(String username);
    Login saveLogin(Login login);
}
