package com.university.management.util;

import com.university.management.repository.ClassRepository;
import com.university.management.repository.CourseRepository;
import com.university.management.repository.LecturerRepository;
import com.university.management.repository.NewsRepository;
import com.university.management.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CodeGenerator {
    
    private final StudentRepository studentRepository;
    private final LecturerRepository lecturerRepository;
    private final CourseRepository courseRepository;
    private final ClassRepository classRepository;
    private final NewsRepository newsRepository;
    
    /**
     * Generate a student code.
     * Format: YY+sequential number (e.g., 210001)
     */
    public String generateStudentCode(String yearCode) {
        if (yearCode == null || yearCode.isEmpty()) {
            // Default to current year if not provided
            yearCode = String.valueOf(java.time.Year.now().getValue() % 100);
        }
        
        Integer maxNumber = studentRepository.findMaxStudentNumber(yearCode);
        int nextNumber = (maxNumber == null) ? 1 : maxNumber + 1;
        
        return yearCode + String.format("%04d", nextNumber);
    }
    
    /**
     * Generate a lecturer code.
     * Format: GV+sequential number (e.g., GV0001)
     */
    public String generateLecturerCode() {
        Integer maxNumber = lecturerRepository.findMaxLecturerNumber();
        int nextNumber = (maxNumber == null) ? 1 : maxNumber + 1;
        
        return "GV" + String.format("%04d", nextNumber);
    }
    
    /**
     * Generate a department code.
     * Format: KH+sequential number (e.g., KH01)
     */
    public String generateDepartmentCode() {
        return "KH" + String.format("%02d", (int) (Math.random() * 99) + 1);
    }
    
    /**
     * Generate a course code.
     * Format: MH+sequential number (e.g., MH001)
     */
    public String generateCourseCode() {
        Integer maxNumber = courseRepository.findMaxCourseNumber();
        int nextNumber = (maxNumber == null) ? 1 : maxNumber + 1;
        
        return "MH" + String.format("%03d", nextNumber);
    }
    
    /**
     * Generate a class code.
     * Format: LH+sequential number (e.g., LH001)
     */
    public String generateClassCode() {
        Integer maxNumber = classRepository.findMaxClassNumber();
        int nextNumber = (maxNumber == null) ? 1 : maxNumber + 1;
        
        return "LH" + String.format("%03d", nextNumber);
    }
    
    /**
     * Generate a news code.
     * Format: TT+sequential number (e.g., TT000001)
     */
    public String generateNewsCode() {
        Integer maxNumber = newsRepository.findMaxNewsNumber();
        int nextNumber = (maxNumber == null) ? 1 : maxNumber + 1;
        
        return "TT" + String.format("%06d", nextNumber);
    }
}
