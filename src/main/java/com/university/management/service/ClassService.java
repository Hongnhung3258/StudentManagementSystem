package com.university.management.service;

import com.example.entity.Class;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClassService {
    List<Class> findAll();
    Class findById(Integer id);
    void saveClass(Class classEntity);
    void deleteClass(Integer id);
    long countClasses();
    String findMaxClassCode();
    Page<Class> findAllPaged(Pageable pageable);
}