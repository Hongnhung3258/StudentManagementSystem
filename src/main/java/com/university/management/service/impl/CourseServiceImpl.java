package com.university.management.service.impl;

import com.university.management.entity.Course;
import com.university.management.repository.CourseRepository;
import com.university.management.service.CourseService;
import com.university.management.util.CodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    
    private final CourseRepository courseRepository;
    private final CodeGenerator codeGenerator;

    @Override
    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course findById(Integer id) {
        return courseRepository.findById(id).orElse(null);
    }

    @Override
    public Course findByCourseCode(String courseCode) {
        return courseRepository.findByCourseCode(courseCode).orElse(null);
    }

    @Override
    public Course findByCourseName(String courseName) {
        return courseRepository.findByCourseName(courseName).orElse(null);
    }

    @Override
    public List<Course> findByDepartmentName(String departmentName) {
        return courseRepository.findByDepartmentName(departmentName);
    }

    @Override
    @Transactional
    public Course saveCourse(Course course) {
        if (course.getCourseCode() == null || course.getCourseCode().isEmpty()) {
            course.setCourseCode(generateCourseCode());
        }
        return courseRepository.save(course);
    }

    @Override
    @Transactional
    public void deleteCourse(Integer id) {
        courseRepository.deleteById(id);
    }

    @Override
    public String generateCourseCode() {
        return codeGenerator.generateCourseCode();
    }

    @Override
    public boolean isCourseCodeExists(String code) {
        return courseRepository.existsByCourseCode(code);
    }

    @Override
    public boolean isCourseNameExists(String name) {
        return courseRepository.existsByCourseName(name);
    }
}
