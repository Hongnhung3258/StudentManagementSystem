package com.university.management.service;

import com.example.entity.ActivityLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ActivityLogService {
    void saveActivityLog(String username, String action, String target, Long targetId);
    List<ActivityLog> findAll();
    List<ActivityLog> findRecentActivities(int limit);
    Page<ActivityLog> findAllPaged(Pageable pageable);
}