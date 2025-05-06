package com.university.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    /**
     * Redirect to login page from root URL
     */
    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }
}