package com.university.management.controller;

import com.university.management.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    private final LoginService loginService;
    
    @Autowired
    public AuthController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @PostMapping("/login")
    public String processLogin(@RequestParam String username, 
                              @RequestParam String password,
                              HttpServletRequest request,
                              RedirectAttributes redirectAttributes) {
        
        if (loginService.authenticate(username, password)) {
            // Authentication successful
            HttpSession session = request.getSession(true);
            session.setAttribute("username", username);
            
            // Redirect to dashboard
            return "redirect:/dashboard";
        } else {
            // Authentication failed
            redirectAttributes.addFlashAttribute("error", "Invalid username or password");
            return "redirect:/login?error";
        }
    }
    
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        
        redirectAttributes.addFlashAttribute("success", "You have been logged out successfully");
        return "redirect:/login?logout";
    }
}