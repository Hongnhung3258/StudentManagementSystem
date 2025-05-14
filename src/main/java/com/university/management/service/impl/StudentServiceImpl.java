package com.university.management.service.impl;

import com.example.entity.Student;
import com.example.entity.Login;
import com.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private LoginService loginService;


    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Page<Student> searchStudents(String keyword, Pageable pageable) {
        return studentRepository.searchStudents(keyword, pageable);
    }

    @Override
    public long countAllStudents() {
        return studentRepository.countAllStudents();
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public void saveStudent(Student student) {
        if (student.getId() == null) { // New student
            // Generate studentId
            String intake = student.getIntake();
            int sequence = studentRepository.findMaxSequenceByIntake(intake) + 1;
            String studentId = CodeGenerator.generateStudentCode(intake, sequence);
            if (studentId.length() > 8) {
                throw new IllegalStateException("Mã sinh viên vượt quá độ dài 8 ký tự");
            }
            student.setStudentId(studentId);

            // Generate and encode password
            String rawPassword = PasswordGenerator.generatePassword(); // 8 characters
            String encodedPassword = passwordEncoder.encode(rawPassword);
            student.setPassword(encodedPassword);
        }
        studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public boolean existsByNationalId(String nationalId) {
        return studentRepository.existsByNationalId(nationalId);
    }

    @Override
    public boolean existsByPhoneNumber(String phoneNumber) {
        return studentRepository.existsByPhoneNumber(phoneNumber);
    }
}