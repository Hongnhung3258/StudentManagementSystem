package com.example.service;

import com.example.entity.Lecturer;
import com.example.entity.Login;
import com.example.repository.LecturerRepository;
import com.university.management.util.PasswordGenerator;
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

    @Autowired
    private LoginService loginService;

    @Override
    public List<Lecturer> findAll() {
        return lecturerRepository.findAll();
    }

    @Override
    public Lecturer findById(Long id) {
        return lecturerRepository.findById(id).orElse(null);
    }

    @Override
    public void saveLecturer(Lecturer lecturer) {
        // Generate lecturer_code if not provided
        if (lecturer.getId() == null && (lecturer.getLecturerCode() == null || lecturer.getLecturerCode().isEmpty())) {
            // Generate lecturerCode
            int sequence = lecturerRepository.findMaxLecturerSequence() + 1;
            String lecturerCode = CodeGenerator.generateLecturerCode(sequence);
            lecturer.setLecturerCode(lecturerCode);
        }
        lecturerRepository.save(lecturer);

        // Synchronize with login table
        String email = lecturer.getLecturerId() + "@tchr.phenikaa-uni.edu.vn";
        Login login = loginService.findByUsername(email);
        if (login == null) {
            login = new Login();
            login.setUsername(email);
            login.setPassword(password); // Password will be encoded in LoginService
            login.setRole("Lecturer");
            loginService.saveLogin(login);
        } else {
            loginService.updatePassword(email, password);
        }
    }

    @Override
    public void deleteLecturer(Long id) {
        Lecturer lecturer = findById(id);
        if (lecturer != null) {
            lecturerRepository.deleteById(id);
            // Remove from login table
            String email = lecturer.getLecturerCode() + "@tchr.phenikaa-uni.edu.vn";
            loginService.deleteLogin(email);
        }
    }

    @Override
    public long countLecturers() {
        return lecturerRepository.count();
    }

    @Override
    public Page<Lecturer> searchLecturers(String keyword, Pageable pageable) {
        return lecturerRepository.searchLecturers(keyword, pageable);
    }

    @Override
    public boolean existsByLecturerCode(String lecturerId) {
        return lecturerRepository.existsByLecturerCode(lecturerId);
    }

    @Override
    public boolean existsByIdNumber(String nationalId) {
        return lecturerRepository.existsByIdNumber(nationalId);
    }

    @Override
    public boolean existsByPhoneNumber(String phoneNumber) {
        return lecturerRepository.existsByPhoneNumber(phoneNumber);
    }

    @Override
    public String generateUniqueLecturerId() {
        String code;
        Random random = new Random();
        do {
            int number = random.nextInt(10000); // Generate number from 0 to 9999
            code = String.format("GV%04d", number); // Format as GV0000 to GV9999
        } while (lecturerRepository.existsByLecturerId(code));
        return code;
    }

    @Override
    public String generateRandomPassword() {
        return PasswordGenerator.generatePassword(8);
    }
}