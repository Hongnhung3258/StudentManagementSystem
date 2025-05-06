package com.university.management.service;

import com.university.management.entity.News;
import java.util.List;

public interface NewsService {
    List<News> findAllNews();
    News findById(Integer id);
    News findByNewsCode(String newsCode);
    News saveNews(News news);
    void deleteNews(Integer id);
    String generateNewsCode();
    boolean isNewsCodeExists(String code);
}
