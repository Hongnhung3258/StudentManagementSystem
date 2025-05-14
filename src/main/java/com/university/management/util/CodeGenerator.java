package com.university.management.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * Utility class for generating unique codes for entities
 */
public class CodeGenerator {
    
    private static final Random random = new Random();
    
     public static String generateLecturerCode(int sequence) {
        return String.format("GV%06d", sequence);
    }
    
    public static String generateDepartmentCode(int sequence) {
        return String.format("KH%04d", sequence);
    }
    

    public static String generateStudentCode(String intake, int sequence) {
        return String.format("SV%s%06d", intake, sequence);
    }

    public static String generateCourseCode(int sequence) {
        return String.format("MH%05d", sequence);
    }
    
    public static String generateClassCode(int sequence) {
        return String.format("LP%05d", sequence);
    }
    
    /**
     * Generate a unique news code
     * Format: TNyyMMddxx where yyMMdd is the current date and xx is a random 2-digit number
     * @return the generated news code
     */
    public static String generateNewsCode() {
        String dateCode = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
        int randomNumber = 10 + random.nextInt(90); // 2-digit number between 10 and 99
        return String.format("TN%s%02d", dateCode, randomNumber);
    }
}