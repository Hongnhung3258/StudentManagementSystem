package com.university.management.repository;

import com.university.management.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findByStudentCode(String studentCode);
    List<Student> findByDepartmentName(String departmentName);
    boolean existsByStudentCode(String studentCode);
    boolean existsByIdNumber(String idNumber);
    
    @Query(value = "SELECT COUNT(*) FROM student", nativeQuery = true)
    Long countStudents();
    
    @Query(value = "SELECT MAX(CAST(SUBSTRING(maSV, 3, 4) AS UNSIGNED)) FROM student WHERE khoa = ?1", nativeQuery = true)
    Integer findMaxStudentNumber(String yearCode);
}
