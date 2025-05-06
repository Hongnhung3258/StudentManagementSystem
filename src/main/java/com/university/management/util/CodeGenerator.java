package com.university.management.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * Utility class for generating unique codes for entities
 */
public class CodeGenerator {
    
    private static final Random random = new Random();
    
    /**
     * Generate a unique student code
     * Format: YYxxxx where YY is the last two digits of the current year and xxxx is a random 4-digit number
     * @return the generated student code
     */
    public static String generateStudentCode() {
        int year = LocalDate.now().getYear() % 100;
        int randomNumber = 1000 + random.nextInt(9000); // 4-digit number between 1000 and 9999
        return String.format("%02d%04d", year, randomNumber);
    }
    
    /**
     * Generate a unique lecturer code
     * Format: GVxxxx where xxxx is a random 4-digit number
     * @return the generated lecturer code
     */
    public static String generateLecturerCode() {
        int randomNumber = 1000 + random.nextInt(9000); // 4-digit number between 1000 and 9999
        return String.format("GV%04d", randomNumber);
    }
    
    /**
     * Generate a unique department code
     * Format: KHxx where xx is a random 2-digit number
     * @return the generated department code
     */
    public static String generateDepartmentCode() {
        int randomNumber = 10 + random.nextInt(90); // 2-digit number between 10 and 99
        return String.format("KH%02d", randomNumber);
    }
    
    /**
     * Generate a unique course code
     * Format: MHxxx where xxx is a random 3-digit number
     * @return the generated course code
     */
    public static String generateCourseCode() {
        int randomNumber = 100 + random.nextInt(900); // 3-digit number between 100 and 999
        return String.format("MH%03d", randomNumber);
    }
    
    /**
     * Generate a unique class code
     * Format: LPxxx where xxx is a random 3-digit number
     * @return the generated class code
     */
    public static String generateClassCode() {
        int randomNumber = 100 + random.nextInt(900); // 3-digit number between 100 and 999
        return String.format("LP%03d", randomNumber);
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