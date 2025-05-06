package com.university.management.service;

import com.university.management.entity.Login;

/**
 * Service for handling login-related operations
 */
public interface LoginService {

    /**
     * Find a login by username
     * @param username the username to search for
     * @return the Login object if found, null otherwise
     */
    Login findByUsername(String username);
    
    /**
     * Create a new login
     * @param login the login to create
     * @return the created login
     */
    Login create(Login login);
    
    /**
     * Update an existing login
     * @param login the login to update
     * @return the updated login
     */
    Login update(Login login);
    
    /**
     * Delete a login by ID
     * @param id the ID of the login to delete
     */
    void delete(Integer id);
    
    /**
     * Check if a username exists
     * @param username the username to check
     * @return true if the username exists, false otherwise
     */
    boolean existsByUsername(String username);
    
    /**
     * Authenticate a user with username and password
     * @param username the username
     * @param password the password (raw, not encoded)
     * @return true if authentication successful, false otherwise
     */
    boolean authenticate(String username, String password);
}