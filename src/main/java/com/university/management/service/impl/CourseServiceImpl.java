package com.university.management.service.impl;

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
    public long countCourses() {
        return courseRepository.count();
    }

    @Override
    public Course findById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    @Override
    public void saveCourse(Course course) {
        if (course.getId() == null && (course.getCourseCode() == null || course.getCourseCode().isEmpty())) {
            // Generate courseCode
            int sequence = courseRepository.findMaxCourseSequence() + 1;
            String courseCode = CodeGenerator.generateCourseCode(sequence);
            course.setCourseCode(courseCode);
        }
        courseRepository.save(course);
    }

    @Override
    public boolean existsByCourseCode(String courseCode) {
        return courseRepository.existsByCourseCode(courseCode);
    }

    @Override
    public Page<Course> searchCourses(String keyword, Pageable pageable) {
        return courseRepository.searchCourses(keyword, pageable);
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public boolean existsByCourseName(String courseName) {
        return courseRepository.existsByCourseName(courseName);
    }
}