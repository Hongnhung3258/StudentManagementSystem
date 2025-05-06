package com.university.management.service.impl;

import com.university.management.entity.Login;
import com.university.management.repository.LoginRepository;
import com.university.management.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    
    private final LoginRepository loginRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean authenticate(String username, String password) {
        Optional<Login> loginOpt = loginRepository.findByUsername(username);
        
        if (loginOpt.isPresent()) {
            // For simplicity, we're directly comparing passwords here
            // In real application, you should use passwordEncoder.matches(password, encodedPassword)
            Login login = loginOpt.get();
            return login.getPassword().equals(password);
        }
        
        return false;
    }

    @Override
    public Login findByUsername(String username) {
        return loginRepository.findByUsername(username).orElse(null);
    }

    @Override
    public Login saveLogin(Login login) {
        // In real application, encode the password before saving
        return loginRepository.save(login);
    }
}
