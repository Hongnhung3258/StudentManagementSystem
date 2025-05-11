package com.example.controller;

import com.example.entity.News;
import com.example.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

/**
 * Controller for managing news-related operations.
 */
@Controller
public class NewsController {

    @Autowired
    private NewsService newsService;

    // Display news list with search and pagination
    @GetMapping("/news")
    public String listNews(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<News> newsList = newsService.searchNews(keyword, pageable);
        model.addAttribute("newsList", newsList);
        model.addAttribute("keyword", keyword);
        model.addAttribute("pageTitle", "Quản lý tin tức");
        return "news";
    }

    // Show form for adding a new news item
    @GetMapping("/news/add")
    public String showAddNewsForm(Model model) {
        model.addAttribute("news", new News());
        model.addAttribute("pageTitle", "Tạo tin tức mới");
        return "create-news";
    }

    // Show form for editing an existing news item
    @GetMapping("/news/edit/{id}")
    public String showEditNewsForm(@PathVariable Integer id, Model model) {
        News news = newsService.findById(id);
        if (news == null) {
            model.addAttribute("errorMessage", "Tin tức không tồn tại");
            return "redirect:/news";
        }
        model.addAttribute("news", news);
        model.addAttribute("pageTitle", "Chỉnh sửa tin tức");
        return "create-news";
    }

    // Handle saving a new or updated news item
    @PostMapping("/news/add")
    public String saveNews(@Valid @ModelAttribute("news") News news,
                           BindingResult result,
                           @RequestParam("tailieuFile") MultipartFile documentFile,
                           @RequestParam("hinhanhFile") MultipartFile imageFile,
                           Model model) {
        if (result.hasErrors()) {
            model.addAttribute("pageTitle", news.getId() != null ? "Chỉnh sửa tin tức" : "Tạo tin tức mới");
            return "create-news";
        }

        try {
            // Validate uniqueness of news code
            if (news.getId() == null && news.getMatin() != null && !news.getMatin().isEmpty() && newsService.existsByMatin(news.getMatin())) {
                result.rejectValue("matin", "error.news", "Mã tin tức đã tồn tại");
            }

            // Validate file sizes
            if (documentFile != null && !documentFile.isEmpty() && documentFile.getSize() > 5 * 1024 * 1024) {
                result.rejectValue("tailieu_url", "error.news", "Tài liệu không được vượt quá 5MB");
            }
            if (imageFile != null && !imageFile.isEmpty() && imageFile.getSize() > 2 * 1024 * 1024) {
                result.rejectValue("hinhanh", "error.news", "Hình ảnh không được vượt quá 2MB");
            }

            if (result.hasErrors()) {
                model.addAttribute("pageTitle", news.getId() != null ? "Chỉnh sửa tin tức" : "Tạo tin tức mới");
                return "create-news";
            }

            newsService.saveNews(news, documentFile, imageFile);
            model.addAttribute("successMessage", "Lưu tin tức thành công");
            return "redirect:/news";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi lưu tin tức: " + e.getMessage());
            model.addAttribute("pageTitle", news.getId() != null ? "Chỉnh sửa tin tức" : "Tạo tin tức mới");
            return "create-news";
        }
    }

    // Handle deleting a news item
    @PostMapping("/news/delete/{id}")
    public String deleteNews(@PathVariable Integer id, Model model) {
        try {
            newsService.deleteNews(id);
            model.addAttribute("successMessage", "Xóa tin tức thành công");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi xóa tin tức: " + e.getMessage());
        }
        return "redirect:/news";
    }
}