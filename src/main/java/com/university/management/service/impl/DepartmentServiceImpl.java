package com.university.management.service;

import com.university.management.entity.Department;
import com.university.management.repository.DepartmentRepository;
import com.university.management.repository.LecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;

@Service
public class DepartmentServiceImple implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private LecturerRepository lecturerRepository;

    public Page<Department> searchDepartments(String keyword, Pageable pageable) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return departmentRepository.findAll(pageable);
        }
        return departmentRepository.findByDepartmentCodeContainingOrDepartmentNameContainingOrHeadLecturerCodeContaining(
                keyword, keyword, keyword, pageable);
    }

    public Department findById(Integer id) {
        return departmentRepository.findById(id).orElse(null);
    }

    public boolean existsByDepartmentName(String departmentName) {
        return departmentRepository.existsByDepartmentName(departmentName);
    }

    public boolean existsByDepartmentCode(String departmentCode) {
        return departmentRepository.existsByDepartmentCode(departmentCode);
    }

    @Transactional
    public void saveDepartment(Department department) {
        // Auto-generate department code if empty
        if (department.getDepartmentCode() == null || department.getDepartmentCode().trim().isEmpty()) {
            String maxCode = departmentRepository.findMaxDepartmentCode();
            int nextCode = 1;
            if (maxCode != null && maxCode.matches("KH\\d{2}")) {
                nextCode = Integer.parseInt(maxCode.substring(2)) + 1;
            }
            department.setDepartmentCode(new DecimalFormat("'KH'00").format(nextCode));
        }
        departmentRepository.save(department);
    }

    @Transactional
    public void deleteDepartment(Integer id) {
        departmentRepository.deleteById(id);
    }

    public boolean isValidHeadLecturer(String headLecturerCode) {
        if (headLecturerCode == null || headLecturerCode.trim().isEmpty()) {
            return true; // Head lecturer is optional
        }
        return lecturerRepository.existsByLecturerCode(headLecturerCode);
    }
}