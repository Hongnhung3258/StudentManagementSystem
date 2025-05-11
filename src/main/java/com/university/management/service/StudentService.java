package com.example.service;

import com.example.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {
    List<Student> findAll();
    Student findById(Integer id);
    void saveStudent(Student student);
    void deleteStudent(Integer id);
    long countStudents();
    Page<Student> searchStudents(String keyword, Pageable pageable);
    boolean existsByStudentCode(String studentCode);
    boolean existsByIdNumber(String idNumber);
    boolean existsByPhoneNumber(String phoneNumber);
}