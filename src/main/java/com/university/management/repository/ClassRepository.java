package com.university.management.repository;

import com.university.management.entity.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClassRepository extends JpaRepository<Class, Integer> {
    Optional<Class> findByClassCode(String classCode);
    List<Class> findByCourseName(String courseName);
    List<Class> findByDepartmentName(String departmentName);
    List<Class> findByLecturerCode(String lecturerCode);
    boolean existsByClassCode(String classCode);
    
    @Query(value = "SELECT COUNT(*) FROM class", nativeQuery = true)
    Long countClasses();
    
    @Query(value = "SELECT MAX(CAST(SUBSTRING(malop, 3, 3) AS UNSIGNED)) FROM class", nativeQuery = true)
    Integer findMaxClassNumber();
}
