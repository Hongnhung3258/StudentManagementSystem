package com.example.service;

import com.example.entity.Lecturer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LecturerService {
    List<Lecturer> findAll();
    Lecturer findById(Integer id);
    void saveLecturer(Lecturer lecturer);
    void deleteLecturer(Integer id);
    long countLecturers();
    Page<Lecturer> searchLecturers(String keyword, Pageable pageable);
}