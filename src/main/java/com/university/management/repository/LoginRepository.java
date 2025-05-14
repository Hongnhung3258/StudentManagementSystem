package com.university.management.repository;

import com.example.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LoginRepository extends JpaRepository<Login, Integer> {

    Optional<Login> findByUsername(String username);
    boolean existsByUsername(String username);
}