package com.university.management.service;

import com.university.management.entity.ActivityLog;
import java.util.List;

public interface ActivityLogService {
    ActivityLog save(ActivityLog activityLog);
    List<ActivityLog> findAllLogs();
    void logActivity(String username, String action, String target, Integer targetId);
}