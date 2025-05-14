package com.university.management.repository;

import com.example.entity.Lecturer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer, Integer> {

    boolean existsByLecturerCode(String lecturerId);

    boolean existsByIdNumber(String nationalId);

    boolean existsByPhoneNumber(String phoneNumber);

    @Query("SELECT l FROM Lecturer l WHERE " +
           "(:keyword IS NULL OR :keyword = '' OR " +
           "LOWER(l.lecturerId) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(l.fullName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(l.nationalId) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(l.phoneNumber) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    Page<Lecturer> searchLecturers(@Param("keyword") String keyword, Pageable pageable);

    long count();
}