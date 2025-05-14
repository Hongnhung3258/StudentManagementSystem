package com.university.management.service;

import com.example.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface NewsService {
    List<News> findAll();
    News findById(Integer id);
    void saveNews(News news, MultipartFile documentFile, MultipartFile imageFile);
    void deleteNews(Integer id);
    boolean existsByMatin(String matin);
    Page<News> searchNews(String keyword, Pageable pageable);
}