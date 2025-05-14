package com.university.management.service;

import java.util.Map;

/**
 * Service for dashboard operations
 */
public interface DashboardService {
    
    Map<String, Long> getStatistics();
}