package com.university.management.service.impl;

import com.university.management.entity.Lecturer;
import com.university.management.repository.LecturerRepository;
import com.university.management.service.LecturerService;
import com.university.management.util.CodeGenerator;
import com.university.management.util.PasswordGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LecturerServiceImpl implements LecturerService {
    
    private final LecturerRepository lecturerRepository;
    private final CodeGenerator codeGenerator;
    private final PasswordGenerator passwordGenerator;

    @Override
    public List<Lecturer> findAllLecturers() {
        return lecturerRepository.findAll();
    }

    @Override
    public Lecturer findById(Integer id) {
        return lecturerRepository.findById(id).orElse(null);
    }

    @Override
    public Lecturer findByLecturerCode(String lecturerCode) {
        return lecturerRepository.findByLecturerCode(lecturerCode).orElse(null);
    }

    @Override
    public List<Lecturer> findByDepartmentName(String departmentName) {
        return lecturerRepository.findByDepartmentName(departmentName);
    }

    @Override
    @Transactional
    public Lecturer saveLecturer(Lecturer lecturer) {
        if (lecturer.getLecturerCode() == null || lecturer.getLecturerCode().isEmpty()) {
            lecturer.setLecturerCode(generateLecturerCode());
        }
        if (lecturer.getPassword() == null || lecturer.getPassword().isEmpty()) {
            lecturer.setPassword(generatePassword());
        }
        return lecturerRepository.save(lecturer);
    }

    @Override
    @Transactional
    public void deleteLecturer(Integer id) {
        lecturerRepository.deleteById(id);
    }

    @Override
    public String generateLecturerCode() {
        return codeGenerator.generateLecturerCode();
    }

    @Override
    public String generatePassword() {
        return passwordGenerator.generateRandomPassword();
    }

    @Override
    public boolean isLecturerCodeExists(String code) {
        return lecturerRepository.existsByLecturerCode(code);
    }

    @Override
    public boolean isIdNumberExists(String idNumber) {
        return lecturerRepository.existsByIdNumber(idNumber);
    }

    @Override
    public Long countLecturers() {
        return lecturerRepository.countLecturers();
    }
}
