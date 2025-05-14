package com.university.management.service;

import com.example.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {
    List<Student> findAll();
    Page<Student> searchStudents(String keyword, Pageable pageable);
    long countAllStudents();
    Student findById(Long id);
    void saveStudent(Student student);
    void deleteStudent(Long id);
    boolean existsByNationalId(String nationalId);
    boolean existsByPhoneNumber(String phoneNumber);
}