package com.example.repository;

import com.example.entity.Lecturer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LecturerRepository extends JpaRepository<Lecturer, Integer> {
    boolean existsByLecturerCode(String lecturerCode);

    @Query("SELECT l FROM Lecturer l WHERE " +
           "(:keyword IS NULL OR :keyword = '' OR " +
           "l.lecturerCode LIKE %:keyword% OR " +
           "l.fullName LIKE %:keyword% OR " +
           "l.departmentName LIKE %:keyword% OR " +
           "l.idNumber LIKE %:keyword%)")
    Page<Lecturer> searchLecturers(@Param("keyword") String keyword, Pageable pageable);
}