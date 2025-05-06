package com.university.management.repository;

import com.university.management.entity.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer, Integer> {
    Optional<Lecturer> findByLecturerCode(String lecturerCode);
    List<Lecturer> findByDepartmentName(String departmentName);
    boolean existsByLecturerCode(String lecturerCode);
    boolean existsByIdNumber(String idNumber);
    
    @Query(value = "SELECT COUNT(*) FROM lecturer", nativeQuery = true)
    Long countLecturers();
    
    @Query(value = "SELECT MAX(CAST(SUBSTRING(magv, 3, 4) AS INTEGER)) FROM lecturer", nativeQuery = true)
    Integer findMaxLecturerNumber();
}
