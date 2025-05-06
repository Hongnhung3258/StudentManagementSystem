package com.university.management.service;

import com.university.management.entity.Class;
import java.util.List;
import java.util.Optional;

/**
 * Service for class operations
 */
public interface ClassService {
    
    /**
     * Count all classes
     * @return the number of classes
     */
    Long countClasses();
    
    /**
     * Find all classes
     * @return list of all classes
     */
    List<Class> findAllClasses();
    
    /**
     * Save or update a class
     * @param classEntity the class to save
     * @return the saved class
     */
    Class saveClass(Class classEntity);
    
    /**
     * Find a class by ID
     * @param id the class ID
     * @return the class if found
     */
    Class findById(Integer id);
    
    /**
     * Delete a class by ID
     * @param id the class ID to delete
     */
    void deleteClass(Integer id);
    
    /**
     * Find a class by its code
     * @param classCode the class code
     * @return the class if found
     */
    Class findByClassCode(String classCode);
    
    /**
     * Find classes by course name
     * @param courseName the course name
     * @return list of classes for the course
     */
    List<Class> findByCourseName(String courseName);
    
    /**
     * Find classes by department name
     * @param departmentName the department name
     * @return list of classes in the department
     */
    List<Class> findByDepartmentName(String departmentName);
    
    /**
     * Find classes by lecturer code
     * @param lecturerCode the lecturer code
     * @return list of classes taught by the lecturer
     */
    List<Class> findByLecturerCode(String lecturerCode);
    
    /**
     * Generate a new class code
     * @return the generated code
     */
    String generateClassCode();
    
    /**
     * Check if a class code already exists
     * @param code the class code to check
     * @return true if exists, false otherwise
     */
    boolean isClassCodeExists(String code);
    
    /**
     * Find classes by course ID
     * @param courseId the course ID
     * @return list of classes for the course
     */
    List<Class> findByCourseId(Integer courseId);
    
    /**
     * Find classes by lecturer ID
     * @param lecturerId the lecturer ID
     * @return list of classes taught by the lecturer
     */
    List<Class> findByLecturerId(Integer lecturerId);
}