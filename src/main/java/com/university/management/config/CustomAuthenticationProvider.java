package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String sql = "SELECT username, password, role FROM login WHERE username = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{username}, (rs, rowNum) -> {
                String password = rs.getString("password");
                String role = rs.getString("role");
                // Gán vai trò với tiền tố ROLE_
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
                return new User(username, password, Collections.singletonList(authority));
            });
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
