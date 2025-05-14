package com.university.management.controller;

import com.university.management.entity.News;
import com.university.management.service.NewsService;
import com.university.management.service.StudentService;
import com.university.management.service.LecturerService;
import com.university.management.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
    private NewsService newsService;

    @GetMapping("/dashboard")
    public String showDashboard(Model model, Authentication authentication) {
        // Check if user has Admin role
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_Admin"))) {
            return "redirect:/management";
        }

        // Statistics for Student and Lecturer roles
        Map<String, Long> stats = new HashMap<>();
        stats.put("studentCount", studentService.countStudents());
        stats.put("lecturerCount", lecturerService.countLecturers());
        stats.put("departmentCount", departmentService.countDepartments());
        model.addAttribute("stats", stats);

        // Recent News
        List<News> recentNews = newsService.findRecentNews(3);
        model.addAttribute("recentNews", recentNews);

        model.addAttribute("title", "Dashboard");
        return "dashboard";
    }
}