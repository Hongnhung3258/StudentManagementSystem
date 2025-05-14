package com.university.management.repository;

import com.example.entity.ActivityLog;
import com.example.repository.ActivityLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ActivityLogServiceImpl implements ActivityLogService {

    @Autowired
    private ActivityLogRepository activityLogRepository;

    @Override
    public void saveActivityLog(String username, String action, String target, Long targetId) {
        ActivityLog log = new ActivityLog();
        log.setUsername(username);
        log.setAction(action);
        log.setTarget(target);
        log.setTargetId(targetId);
        log.setTimestamp(LocalDateTime.now());
        activityLogRepository.save(log);
    }

    @Override
    public List<ActivityLog> findAll() {
        return activityLogRepository.findAll();
    }

    @Override
    public List<ActivityLog> findRecentActivities(int limit) {
        return activityLogRepository.findAll(PageRequest.of(0, limit, Sort.by(Sort.Direction.DESC, "timestamp"))).getContent();
    }

    @Override
    public Page<ActivityLog> findAll(Pageable pageable) {
        return activityLogRepository.findAll(pageable);
    }
}