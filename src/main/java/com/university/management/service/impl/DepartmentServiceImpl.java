package com.example.service;

import com.example.entity.Department;
import com.example.repository.DepartmentRepository;
import com.example.repository.LecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private LecturerRepository lecturerRepository;

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Department findById(String departmentName) {
        return departmentRepository.findById(departmentName).orElse(null);
    }

    @Override
    public void saveDepartment(Department department) {
        departmentRepository.save(department);
    }

    @Override
    public void deleteDepartment(String departmentName) {
        departmentRepository.deleteById(departmentName);
    }

    @Override
    public long countDepartments() {
        return departmentRepository.count();
    }

    @Override
    public String findMaxDepartmentCode() {
        return departmentRepository.findMaxDepartmentCode();
    }

    @Override
    public boolean isValidHeadLecturerCode(String headLecturerCode) {
        if (headLecturerCode == null || headLecturerCode.trim().isEmpty()) {
            return true; // Tùy chọn, không bắt buộc
        }
        return lecturerRepository.existsByLecturerId(headLecturerCode);
    }
}