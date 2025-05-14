package com.university.management.controller;

import com.example.service.ActivityLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ActivityLogController {

    @Autowired
    private ActivityLogService activityLogService;

    @GetMapping("/activity-logs")
    public String showActivityLogs(Model model,
                                  @RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "timestamp"));
        model.addAttribute("logs", activityLogService.findAllPaged(pageable));
        return "activity-logs";
    }
}