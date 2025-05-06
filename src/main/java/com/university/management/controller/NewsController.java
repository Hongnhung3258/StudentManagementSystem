package com.university.management.controller;

import com.university.management.entity.News;
import com.university.management.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/news")
public class NewsController {
    
    private final NewsService newsService;
    
    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }
    
    @GetMapping
    public String listNews(Model model) {
        List<News> newsList = newsService.findAllNews();
        model.addAttribute("newsList", newsList);
        return "news/list";
    }
    
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("news", new News());
        return "news/add";
    }
    
    @PostMapping("/add")
    public String addNews(
            @ModelAttribute News news,
            BindingResult result) {
        
        if (news.getNewsCode() != null && newsService.isNewsCodeExists(news.getNewsCode())) {
            result.rejectValue("newsCode", "error.news", "News code already exists");
        }
        
        if (result.hasErrors()) {
            return "news/add";
        }
        
        newsService.saveNews(news);
        return "redirect:/news";
    }
    
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        News news = newsService.findById(id);
        if (news == null) {
            return "redirect:/news";
        }
        
        model.addAttribute("news", news);
        return "news/add";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteNews(@PathVariable Integer id) {
        newsService.deleteNews(id);
        return "redirect:/news";
    }
}
