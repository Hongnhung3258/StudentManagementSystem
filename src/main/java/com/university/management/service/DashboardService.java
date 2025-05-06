package com.university.management.service;

import java.util.Map;

/**
 * Service for dashboard operations
 */
public interface DashboardService {
    
    /**
     * Get statistics for dashboard display
     * @return Map of statistics with counts
     */
    Map<String, Long> getStatistics();
}