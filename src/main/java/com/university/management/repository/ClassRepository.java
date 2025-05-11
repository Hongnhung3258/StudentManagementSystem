package com.example.repository;

import com.example.entity.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClassRepository extends JpaRepository<Class, Integer> {
    @Query("SELECT MAX(c.classCode) FROM Class c")
    String findMaxClassCode();
}