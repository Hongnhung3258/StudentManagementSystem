package com.university.management.service;

import com.university.management.entity.Lecturer;
import java.util.List;

/**
 * Service for lecturer operations
 */
public interface LecturerService {
    
    /**
     * Count all lecturers
     * @return the number of lecturers
     */
    Long countLecturers();
    
    /**
     * Find all lecturers
     * @return list of all lecturers
     */
    List<Lecturer> findAllLecturers();
    
    /**
     * Save or update a lecturer
     * @param lecturer the lecturer to save
     * @return the saved lecturer
     */
    Lecturer saveLecturer(Lecturer lecturer);
    
    /**
     * Find a lecturer by ID
     * @param id the lecturer ID
     * @return the lecturer if found, or null if not found
     */
    Lecturer findById(Integer id);
    
    /**
     * Find lecturer by lecturer code
     * @param lecturerCode the lecturer code
     * @return the lecturer if found, or null if not found
     */
    Lecturer findByLecturerCode(String lecturerCode);
    
    /**
     * Find lecturers by department name
     * @param departmentName the department name
     * @return list of lecturers in the department
     */
    List<Lecturer> findByDepartmentName(String departmentName);
    
    /**
     * Delete a lecturer by ID
     * @param id the lecturer ID to delete
     */
    void deleteLecturer(Integer id);
    
    /**
     * Generate a lecturer code
     * @return the generated code
     */
    String generateLecturerCode();
    
    /**
     * Check if a lecturer ID number already exists
     * @param idNumber the ID number to check
     * @return true if exists, false otherwise
     */
    boolean isIdNumberExists(String idNumber);
    
    /**
     * Check if a lecturer code already exists
     * @param code the lecturer code to check
     * @return true if exists, false otherwise
     */
    boolean isLecturerCodeExists(String code);
    
    /**
     * Generate new lecturer ID
     * @return the generated ID
     */
    String generateLecturerId();
    
    /**
     * Generate a new password
     * @return the generated password
     */
    String generatePassword();
    
    /**
     * Find lecturers by department ID
     * @param departmentId the department ID
     * @return list of lecturers in the department
     */
    List<Lecturer> findByDepartmentId(Integer departmentId);
    
    /**
     * Find lecturers available for department head position
     * @param departmentId the department ID
     * @return list of eligible lecturers
     */
    List<Lecturer> findAvailableForDepartmentHead(Integer departmentId);
    
    /**
     * Check if a lecturer can be deleted
     * @param id the lecturer ID
     * @return true if can be deleted, false otherwise
     */
    boolean canDelete(Integer id);
    
    /**
     * Find eligible lecturers for course teaching
     * @param departmentId the department ID
     * @return list of eligible lecturers
     */
    List<Lecturer> findEligibleForCourse(Integer departmentId);
}