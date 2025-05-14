package com.university.management.repository;

import com.example.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE " +
           "(:keyword IS NULL OR " +
           "s.studentId LIKE %:keyword% OR " +
           "s.fullName LIKE %:keyword% OR " +
           "s.phoneNumber LIKE %:keyword% OR " +
           "s.nationalId LIKE %:keyword%)")
    Page<Student> searchStudents(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT COUNT(s) FROM Student s")
    long countAllStudents();

    @Query("SELECT COALESCE(MAX(CAST(SUBSTRING(s.studentId, 5) AS INTEGER)), 0) FROM Student s WHERE s.intake = :intake")
    int findMaxSequenceByIntake(@Param("intake") String intake);

    boolean existsByNationalId(String nationalId);

    boolean existsByPhoneNumber(String phoneNumber);
}