package com.university.management.service.impl;

import com.university.management.entity.News;
import com.university.management.repository.NewsRepository;
import com.university.management.service.NewsService;
import com.university.management.util.CodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    
    private final NewsRepository newsRepository;

    @Autowired
    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public List<News> findAllNews() {
        return newsRepository.findAll();
    }

    @Override
    public News findById(Integer id) {
        return newsRepository.findById(id).orElse(null);
    }

    @Override
    public News findByNewsCode(String newsCode) {
        return newsRepository.findByNewsCode(newsCode).orElse(null);
    }

    @Override
    @Transactional
    public News saveNews(News news) {
        if (news.getNewsCode() == null || news.getNewsCode().isEmpty()) {
            news.setNewsCode(generateNewsCode());
        }
        if (news.getCreatedAt() == null) {
            news.setCreatedAt(LocalDateTime.now());
        }
        return newsRepository.save(news);
    }

    @Override
    @Transactional
    public void deleteNews(Integer id) {
        newsRepository.deleteById(id);
    }

    @Override
    public String generateNewsCode() {
        return CodeGenerator.generateNewsCode();
    }

    @Override
    public boolean isNewsCodeExists(String code) {
        return newsRepository.existsByNewsCode(code);
    }
}
