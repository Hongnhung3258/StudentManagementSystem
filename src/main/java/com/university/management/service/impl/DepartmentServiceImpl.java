package com.university.management.service.impl;

import com.university.management.entity.Department;
import com.university.management.repository.DepartmentRepository;
import com.university.management.service.DepartmentService;
import com.university.management.util.CodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    
    private final DepartmentRepository departmentRepository;
    private final CodeGenerator codeGenerator;

    @Override
    public List<Department> findAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department findById(Integer id) {
        return departmentRepository.findById(id).orElse(null);
    }

    @Override
    public Department findByDepartmentCode(String departmentCode) {
        return departmentRepository.findByDepartmentCode(departmentCode).orElse(null);
    }

    @Override
    public Department findByDepartmentName(String departmentName) {
        return departmentRepository.findByDepartmentName(departmentName).orElse(null);
    }

    @Override
    @Transactional
    public Department saveDepartment(Department department) {
        if (department.getDepartmentCode() == null || department.getDepartmentCode().isEmpty()) {
            department.setDepartmentCode(generateDepartmentCode());
        }
        return departmentRepository.save(department);
    }

    @Override
    @Transactional
    public void deleteDepartment(Integer id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public String generateDepartmentCode() {
        // Department code is 'KH' followed by a 2-digit number
        return codeGenerator.generateDepartmentCode();
    }

    @Override
    public boolean isDepartmentCodeExists(String code) {
        return departmentRepository.existsByDepartmentCode(code);
    }

    @Override
    public boolean isDepartmentNameExists(String name) {
        return departmentRepository.existsByDepartmentName(name);
    }
}
