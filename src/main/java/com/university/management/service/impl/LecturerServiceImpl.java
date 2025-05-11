package com.example.service;

import com.example.entity.Lecturer;
import com.example.repository.LecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class LecturerServiceImpl implements LecturerService {

    @Autowired
    private LecturerRepository lecturerRepository;

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    @Override
    public List<Lecturer> findAll() {
        return lecturerRepository.findAll();
    }

    @Override
    public Lecturer findById(Integer id) {
        return lecturerRepository.findById(id).orElse(null);
    }

    @Override
    public void saveLecturer(Lecturer lecturer) {
        // Tạo mật khẩu ngẫu nhiên nếu chưa có
        if (lecturer.getPassword() == null || lecturer.getPassword().isEmpty()) {
            lecturer.setPassword(generateRandomPassword());
        }
        lecturerRepository.save(lecturer);
    }

    @Override
    public void deleteLecturer(Integer id) {
        lecturerRepository.deleteById(id);
    }

    @Override
    public long countLecturers() {
        return lecturerRepository.count();
    }

    @Override
    public Page<Lecturer> searchLecturers(String keyword, Pageable pageable) {
        return lecturerRepository.searchLecturers(keyword, pageable);
    }

    private String generateRandomPassword() {
        StringBuilder password = new StringBuilder(8);
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            password.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return password.toString();
    }
}