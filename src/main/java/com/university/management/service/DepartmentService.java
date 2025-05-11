package com.example.service;

import com.example.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DepartmentService {
    List<Department> findAll();
    Department findById(Long id);
    void saveDepartment(Department department);
    void deleteDepartment(Long id);
    long countDepartments();
    String findMaxDepartmentCode();
    Page<Department> findAllPaged(Pageable pageable);
    boolean isValidHeadLecturerCode(String headLecturerCode);
}