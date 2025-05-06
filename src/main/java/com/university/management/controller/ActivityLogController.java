package com.university.management.controller;

import com.university.management.entity.ActivityLog;
import com.university.management.service.ActivityLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/activity-logs")
public class ActivityLogController {

    private final ActivityLogService activityLogService;

    @Autowired
    public ActivityLogController(ActivityLogService activityLogService) {
        this.activityLogService = activityLogService;
    }

    @GetMapping
    public String showActivityLogs(Model model) {
        List<ActivityLog> logs = activityLogService.findAllLogs();
        model.addAttribute("logs", logs);
        model.addAttribute("pageTitle", "Nhật ký hoạt động");
        return "activity/logs";
    }
}