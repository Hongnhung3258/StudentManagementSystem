package com.university.management.controller;

import com.university.management.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    
    // Đã chuyển route "/" sang IndexController để tránh xung đột
    
    @PostMapping("/login")
    public String processLogin(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model,
            HttpSession session) {
        
        // Xác thực đăng nhập
        boolean authenticated = loginService.authenticate(username, password);
        
        if (authenticated) {
            // Lưu thông tin người dùng vào session
            session.setAttribute("username", username);
            session.setAttribute("authenticated", true);
            
            // Chuyển hướng đến trang dashboard
            return "redirect:/dashboard";
        } else {
            // Đăng nhập thất bại
            model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng");
            return "login";
        }
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout";
    }
}