package com.university.management.repository;

import com.university.management.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LoginRepository extends JpaRepository<Login, Integer> {
    
    /**
     * Find a login entry by username
     * @param username the username to search for
     * @return an Optional containing the login if found
     */
    Optional<Login> findByUsername(String username);
    
    /**
     * Check if a username exists
     * @param username the username to check
     * @return true if the username exists, false otherwise
     */
    boolean existsByUsername(String username);
}