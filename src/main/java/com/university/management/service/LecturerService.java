package com.university.management.service;

import com.example.entity.Lecturer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LecturerService {

    List<Lecturer> findAll();

    Lecturer findById(Long id);

    void saveLecturer(Lecturer lecturer);

    void deleteLecturer(Long id);

    long countLecturers();

    Page<Lecturer> searchLecturers(String keyword, Pageable pageable);

    boolean existsByLecturerCode(String lecturerId);

    boolean existsByIdNumber(String nationalId);

    boolean existsByPhoneNumber(String phoneNumber);

    String generateUniqueLecturerId();

    String generateRandomPassword();
}