package com.university.management.service.impl;

import com.university.management.entity.Login;
import com.university.management.repository.LoginRepository;
import com.university.management.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    private final LoginRepository loginRepository;
    private final PasswordEncoder passwordEncoder;
    
    @Autowired
    public LoginServiceImpl(LoginRepository loginRepository, PasswordEncoder passwordEncoder) {
        this.loginRepository = loginRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    public Login findByUsername(String username) {
        return loginRepository.findByUsername(username).orElse(null);
    }
    
    @Override
    public Login create(Login login) {
        // Encode the password before saving
        login.setPassword(passwordEncoder.encode(login.getPassword()));
        return loginRepository.save(login);
    }
    
    @Override
    public Login update(Login login) {
        // Check if the password has been changed
        Login existingLogin = loginRepository.findById(login.getId()).orElse(null);
        if (existingLogin != null && !existingLogin.getPassword().equals(login.getPassword())) {
            // If password has changed, encode it
            login.setPassword(passwordEncoder.encode(login.getPassword()));
        }
        return loginRepository.save(login);
    }
    
    @Override
    public void delete(Integer id) {
        loginRepository.deleteById(id);
    }
    
    @Override
    public boolean existsByUsername(String username) {
        return loginRepository.existsByUsername(username);
    }
    
    @Override
    public boolean authenticate(String username, String password) {
        Login login = findByUsername(username);
        if (login == null) {
            return false;
        }
        
        // For the first login with plain text passwords, we'll need to update them
        if (!login.getPassword().startsWith("$2a$")) {
            // If the password is not BCrypt-encoded, compare directly
            if (login.getPassword().equals(password)) {
                // Update the password to be BCrypt-encoded for future logins
                login.setPassword(passwordEncoder.encode(password));
                loginRepository.save(login);
                return true;
            }
            return false;
        } else {
            // If the password is already BCrypt-encoded, use the encoder to compare
            return passwordEncoder.matches(password, login.getPassword());
        }
    }
}