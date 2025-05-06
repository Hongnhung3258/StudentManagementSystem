package com.university.management.service.impl;

import com.university.management.entity.ActivityLog;
import com.university.management.repository.ActivityLogRepository;
import com.university.management.service.ActivityLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ActivityLogServiceImpl implements ActivityLogService {

    private final ActivityLogRepository activityLogRepository;

    @Autowired
    public ActivityLogServiceImpl(ActivityLogRepository activityLogRepository) {
        this.activityLogRepository = activityLogRepository;
    }

    @Override
    public ActivityLog save(ActivityLog activityLog) {
        return activityLogRepository.save(activityLog);
    }

    @Override
    public List<ActivityLog> findAllLogs() {
        return activityLogRepository.findAllByOrderByTimestampDesc();
    }

    @Override
    public void logActivity(String username, String action, String target, Integer targetId) {
        ActivityLog log = new ActivityLog();
        log.setUsername(username);
        log.setAction(action);
        log.setTarget(target);
        log.setTargetId(targetId);
        log.setTimestamp(LocalDateTime.now());
        activityLogRepository.save(log);
    }
}