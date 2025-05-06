package com.university.management.service;

import com.university.management.entity.Student;
import java.util.List;

/**
 * Service for student operations
 */
public interface StudentService {
    
    /**
     * Count all students
     * @return the number of students
     */
    Long countStudents();
    
    /**
     * Find all students
     * @return list of all students
     */
    List<Student> findAllStudents();
    
    /**
     * Save or update a student
     * @param student the student to save
     * @return the saved student
     */
    Student saveStudent(Student student);
    
    /**
     * Find a student by ID
     * @param id the student ID
     * @return the student if found, or null if not found
     */
    Student findById(Integer id);
    
    /**
     * Find student by student code
     * @param studentCode the student code
     * @return the student if found, or null if not found
     */
    Student findByStudentCode(String studentCode);
    
    /**
     * Find students by department name
     * @param departmentName the department name
     * @return list of students in the department
     */
    List<Student> findByDepartmentName(String departmentName);
    
    /**
     * Delete a student by ID
     * @param id the student ID to delete
     */
    void deleteStudent(Integer id);
    
    /**
     * Generate a student code based on year code
     * @param yearCode the year code (e.g., "20" for 2020)
     * @return the generated student code
     */
    String generateStudentCode(String yearCode);
    
    /**
     * Generate a new password
     * @return the generated password
     */
    String generatePassword();
    
    /**
     * Check if a student code already exists
     * @param code the student code to check
     * @return true if exists, false otherwise
     */
    boolean isStudentCodeExists(String code);
    
    /**
     * Check if a student ID number already exists
     * @param idNumber the ID number to check
     * @return true if exists, false otherwise
     */
    boolean isIdNumberExists(String idNumber);
}