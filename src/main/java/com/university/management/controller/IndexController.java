package com.university.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


public class IndexController {

    public String index() {
        return "redirect:/login";
    }
}