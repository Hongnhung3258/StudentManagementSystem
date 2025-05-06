package com.university.management.service;

import com.university.management.entity.Department;
import java.util.List;

public interface DepartmentService {
    List<Department> findAllDepartments();
    Department findById(Integer id);
    Department findByDepartmentCode(String departmentCode);
    Department findByDepartmentName(String departmentName);
    Department saveDepartment(Department department);
    void deleteDepartment(Integer id);
    String generateDepartmentCode();
    boolean isDepartmentCodeExists(String code);
    boolean isDepartmentNameExists(String name);
}
