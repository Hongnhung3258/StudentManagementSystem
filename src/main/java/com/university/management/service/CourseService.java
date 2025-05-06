package com.university.management.service;

import com.university.management.entity.Course;
import java.util.List;

public interface CourseService {
    List<Course> findAllCourses();
    Course findById(Integer id);
    Course findByCourseCode(String courseCode);
    Course findByCourseName(String courseName);
    List<Course> findByDepartmentName(String departmentName);
    Course saveCourse(Course course);
    void deleteCourse(Integer id);
    String generateCourseCode();
    boolean isCourseCodeExists(String code);
    boolean isCourseNameExists(String name);
}
