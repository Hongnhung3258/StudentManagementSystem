package com.example.repository;

import com.example.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repository for Student entity, providing basic and custom query methods.
 */
public interface StudentRepository extends JpaRepository<Student, Integer> {

    boolean existsByStudentCode(String studentCode);

    boolean existsByIdNumber(String idNumber);

    boolean existsByPhoneNumber(String phoneNumber);

    @Query("SELECT s FROM Student s WHERE " +
           "(:keyword IS NULL OR :keyword = '' OR " +
           "s.studentCode LIKE %:keyword% OR " +
           "s.fullName LIKE %:keyword% OR " +
           "s.departmentName LIKE %:keyword% OR " +
           "s.idNumber LIKE %:keyword%)")
    Page<Student> searchStudents(@Param("keyword") String keyword, Pageable pageable);
}