package com.university.management.util;

import java.security.SecureRandom;

/**
 * Utility class for generating random passwords
 */
public class PasswordGenerator {
    
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL = "!@#$%^&*()-_=+[]{}|;:,.<>?";
    private static final String ALL = LOWERCASE + UPPERCASE + DIGITS + SPECIAL;
    
    private static final SecureRandom random = new SecureRandom();
    
    /**
     * Generate a random password with specified length
     * @param length the length of the password
     * @return the generated password
     */
    public static String generatePassword(int length) {
        if (length < 8) {
            length = 8; // Minimum secure password length
        }
        
        StringBuilder password = new StringBuilder();
        
        // Ensure at least one character from each category
        password.append(LOWERCASE.charAt(random.nextInt(LOWERCASE.length())));
        password.append(UPPERCASE.charAt(random.nextInt(UPPERCASE.length())));
        password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        password.append(SPECIAL.charAt(random.nextInt(SPECIAL.length())));
        
        // Add remaining characters randomly
        for (int i = 4; i < length; i++) {
            password.append(ALL.charAt(random.nextInt(ALL.length())));
        }
        
        // Shuffle the password characters
        char[] passwordArray = password.toString().toCharArray();
        for (int i = 0; i < passwordArray.length; i++) {
            int j = random.nextInt(passwordArray.length);
            char temp = passwordArray[i];
            passwordArray[i] = passwordArray[j];
            passwordArray[j] = temp;
        }
        
        return new String(passwordArray);
    }
    
    /**
     * Generate a random password with default length of 8 characters
     * @return the generated password
     */
    public static String generatePassword() {
        return generatePassword(8);
    }
    
    /**
     * Generate a simple password for initial accounts (less complex for easier use)
     * @return the generated simple password
     */
    public static String generateSimplePassword() {
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        }
        return password.toString();
    }
}