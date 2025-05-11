package com.example.service;

import com.example.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service interface for Course operations.
 */
public interface CourseService {
    List<Course> findAll();
    Course findById(Integer id);
    void saveCourse(Course course);
    void deleteCourse(Integer id);
    long countCourses();
    Page<Course> searchCourses(String keyword, Pageable pageable);
    boolean existsByCourseCode(String courseCode);
    boolean existsByCourseName(String courseName);
}