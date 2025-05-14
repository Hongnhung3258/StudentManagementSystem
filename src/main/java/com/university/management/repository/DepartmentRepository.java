package com.university.management.repository;

import com.example.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    boolean existsByDepartmentCode(String departmentCode);

    boolean existsByDepartmentName(String departmentName);

   Page<Department> findByDepartmentCodeContainingOrDepartmentNameContainingOrHeadLecturerCodeContaining(
            String code, String name, String headCode, Pageable pageable);

    @Query("SELECT MAX(d.departmentCode) FROM Department d WHERE d.departmentCode LIKE 'KH%'")
    String findMaxDepartmentCode();
}