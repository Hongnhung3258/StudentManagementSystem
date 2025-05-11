package com.example.repository;

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

    @Query("SELECT c FROM Course c WHERE " +
           "(:keyword IS NULL OR :keyword = '' OR " +
           "c.courseCode LIKE %:keyword% OR " +
           "c.courseName LIKE %:keyword% OR " +
           "c.departmentName LIKE %:keyword%)")
    Page<Course> searchCourses(@Param("keyword") String keyword, Pageable pageable);
}