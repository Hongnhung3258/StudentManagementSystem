package com.example.controller;

import com.example.entity.ActivityLog;
import com.example.entity.News;
import com.example.service.ActivityLogService;
import com.example.service.ClassService;
import com.example.service.DepartmentService;
import com.example.service.LecturerService;
import com.example.service.NewsService;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DashboardController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private LecturerService lecturerService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private ClassService classService;

    @Autowired
    private ActivityLogService activityLogService;

    @Autowired
    private NewsService newsService;

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        // Statistics
        Map<String, Long> stats = new HashMap<>();
        stats.put("studentCount", studentService.countStudents());
        stats.put("lecturerCount", lecturerService.countLecturers());
        stats.put("departmentCount", departmentService.countDepartments());
        stats.put("classCount", classService.countClasses());
        model.addAttribute("stats", stats);

        // Recent Activities
        List<ActivityLog> recentActivities = activityLogService.findRecentActivities(3);
        model.addAttribute("recentActivities", recentActivities);

        // Recent News
        List<News> recentNews = newsService.findRecentNews(3);
        model.addAttribute("recentNews", recentNews);

        model.addAttribute("title", "Dashboard");
        return "dashboard";
    }
}