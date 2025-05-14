package com.university.management.service.impl;

import com.example.entity.News;
import com.example.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

/**
 * Implementation of NewsService for handling news-related operations.
 */
@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;

    private static final String UPLOAD_DIR = "uploads/";
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    @Override
    public List<News> findAll() {
        return newsRepository.findAll();
    }

    @Override
    public News findById(Integer id) {
        return newsRepository.findById(id).orElse(null);
    }

    @Override
    public void saveNews(News news, MultipartFile documentFile, MultipartFile imageFile) {
        // Generate news code if not provided
        if (news.getMatin() == null || news.getMatin().isEmpty()) {
            news.setMatin(generateNewsCode());
        }

        // Handle document file upload
        if (documentFile != null && !documentFile.isEmpty()) {
            try {
                String fileName = System.currentTimeMillis() + "_" + documentFile.getOriginalFilename();
                Path filePath = Paths.get(UPLOAD_DIR + fileName);
                Files.createDirectories(filePath.getParent());
                Files.write(filePath, documentFile.getBytes());
                news.setTailieu_url("/" + UPLOAD_DIR + fileName);
            } catch (IOException e) {
                throw new RuntimeException("Lỗi khi lưu tài liệu: " + e.getMessage());
            }
        }

        // Handle image file upload
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
                Path filePath = Paths.get(UPLOAD_DIR + fileName);
                Files.createDirectories(filePath.getParent());
                Files.write(filePath, imageFile.getBytes());
                news.setHinhanh("/" + UPLOAD_DIR + fileName);
            } catch (IOException e) {
                throw new RuntimeException("Lỗi khi lưu hình ảnh: " + e.getMessage());
            }
        }

        newsRepository.save(news);
    }

    @Override
    public void deleteNews(Integer id) {
        News news = findById(id);
        if (news != null) {
            // Delete files from storage
            if (news.getTailieu_url() != null) {
                new File(news.getTailieu_url().substring(1)).delete();
            }
            if (news.getHinhanh() != null) {
                new File(news.getHinhanh().substring(1)).delete();
            }
            newsRepository.deleteById(id);
        }
    }

    @Override
    public boolean existsByMatin(String matin) {
        return newsRepository.existsByMatin(matin);
    }

    @Override
    public Page<News> searchNews(String keyword, Pageable pageable) {
        return newsRepository.searchNews(keyword, pageable);
    }

    private String generateNewsCode() {
        String prefix = "TT";
        String suffix;
        do {
            suffix = String.format("%06d", new Random().nextInt(1000000));
        } while (newsRepository.existsByMatin(prefix + suffix));
        return prefix + suffix;
    }
}