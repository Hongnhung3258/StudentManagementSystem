package com.university.management.repository;

import com.example.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repository for Course entity, providing basic and custom query methods.
 */
public interface CourseRepository extends JpaRepository<Course, Integer> {

    boolean existsByCourseCode(String courseCode);

    boolean existsByCourseName(String courseName);

    @Query("SELECT COALESCE(MAX(CAST(SUBSTRING(c.courseCode, 3) AS INTEGER)), 0) FROM Course c WHERE c.courseCode LIKE 'MH%'")
    int findMaxCourseSequence();

    @Query("SELECT c FROM Course c WHERE " +
           "(:keyword IS NULL OR " +
           "c.courseCode LIKE %:keyword% OR " +
           "c.courseName LIKE %:keyword% OR " +
           "c.departmentName LIKE %:keyword%)")
    Page<Course> searchCourses(@Param("keyword") String keyword, Pageable pageable);
}