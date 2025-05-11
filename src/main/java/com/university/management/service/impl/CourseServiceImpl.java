package com.example.service;

import com.example.entity.Course;
import com.example.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * Implementation of CourseService for handling course-related operations.
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course findById(Integer id) {
        return courseRepository.findById(id).orElse(null);
    }

    @Override
    public void saveCourse(Course course) {
        // Generate course code if not provided
        if (course.getCourseCode() == null || course.getCourseCode().isEmpty()) {
            course.setCourseCode(generateCourseCode());
        }
        courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Integer id) {
        courseRepository.deleteById(id);
    }

    @Override
    public long countCourses() {
        return courseRepository.count();
    }

    @Override
    public Page<Course> searchCourses(String keyword, Pageable pageable) {
        return courseRepository.searchCourses(keyword, pageable);
    }

    @Override
    public boolean existsByCourseCode(String courseCode) {
        return courseRepository.existsByCourseCode(courseCode);
    }

    @Override
    public boolean existsByCourseName(String courseName) {
        return courseRepository.existsByCourseName(courseName);
    }

    private String generateCourseCode() {
        String prefix = "MH";
        String suffix;
        do {
            suffix = String.format("%03d", new Random().nextInt(1000));
        } while (courseRepository.existsByCourseCode(prefix + suffix));
        return prefix + suffix;
    }
}