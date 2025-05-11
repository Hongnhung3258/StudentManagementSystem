```java
package com.example.service;

import com.example.entity.Login;
import com.example.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Triển khai dịch vụ cho các thao tác liên quan đến đăng nhập.
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Login findByUsername(String username) {
        return loginRepository.findByUsername(username).orElse(null);
    }

    @Override
    public void saveLogin(Login login) {
        if (login.getPassword() != null && !login.getPassword().isEmpty()) {
            login.setPassword(passwordEncoder.encode(login.getPassword()));
        }
        loginRepository.save(login);
    }

    @Override
    public boolean existsByUsername(String username) {
        return loginRepository.existsByUsername(username);
    }

    @Override
    public void updatePassword(String username, String newPassword) {
        Login login = findByUsername(username);
        if (login != null) {
            login.setPassword(passwordEncoder.encode(newPassword));
            loginRepository.save(login);
        } else {
            throw new IllegalArgumentException("Tên đăng nhập không tồn tại: " + username);
        }
    }
}
```