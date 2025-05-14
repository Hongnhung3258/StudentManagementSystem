package com.university.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller xử lý các trang chủ và điều hướng cơ bản
 */
@Controller
public class HomeController {

    /**
     * Xử lý request đến trang chủ
     * @param model Model để truyền dữ liệu đến view
     * @return Tên view template
     */
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Hệ thống Quản lý Đại học");
        model.addAttribute("message", "Chào mừng đến với Hệ thống Quản lý Đại học");
        return "home";
    }
    
    /**
     * Xử lý request đến trang đăng nhập
     * @return Tên view template đăng nhập
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}