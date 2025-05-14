package com.university.management.controller;

import com.university.management.entity.ActivityLog;
import com.university.management.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ManagementController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private LecturerService lecturerService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private ClassService classService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private ActivityLogService activityLogService;

    @GetMapping("/management")
    public String showManagement(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            Model model) {
        // Statistics
        Map<String, Long> stats = new HashMap<>();
        stats.put("studentCount", studentService.countStudents());
        stats.put("lecturerCount", lecturerService.countLecturers());
        stats.put("departmentCount", departmentService.countDepartments());
        stats.put("classCount", classService.countClasses());
        stats.put("courseCount", courseService.countCourses());
        model.addAttribute("stats", stats);

        // Activity Logs with Pagination
        Pageable pageable = PageRequest.of(page, size);
        Page<ActivityLog> logs = activityLogService.findAllLogs(pageable);
        model.addAttribute("logs", logs);

        model.addAttribute("pageTitle", "Trang chủ");
        return "admin/management";
    }
    
}