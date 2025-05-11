package com.example.service;

import com.example.entity.Student;
import com.example.entity.Login;
import com.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * Implementation of StudentService for handling student-related operations.
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private LoginService loginService;

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(Integer id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public void saveStudent(Student student) {
        // Generate student code if not provided
        if (student.getStudentCode() == null || student.getStudentCode().isEmpty()) {
            student.setStudentCode(generateStudentCode(student.getYearCode()));
        }
        // Generate random password if not provided
        String password = student.getPassword();
        if (password == null || password.isEmpty()) {
            password = generateRandomPassword();
            student.setPassword(password);
        }
        studentRepository.save(student);

        // Synchronize with login table
        Login login = loginService.findByUsername(student.getStudentCode());
        if (login == null) {
            login = new Login(student.getStudentCode(), password);
            loginService.saveLogin(login);
        } else {
            loginService.updatePassword(student.getStudentCode(), password);
        }
    }

    @Override
    public void deleteStudent(Integer id) {
        Student student = findById(id);
        if (student != null) {
            studentRepository.deleteById(id);
            // Optionally remove from login table
            // loginService.deleteByUsername(student.getStudentCode());
        }
    }

    @Override
    public long countStudents() {
        return studentRepository.count();
    }

    @Override
    public Page<Student> searchStudents(String keyword, Pageable pageable) {
        return studentRepository.searchStudents(keyword, pageable);
    }

    @Override
    public boolean existsByStudentCode(String studentCode) {
        return studentRepository.existsByStudentCode(studentCode);
    }

    @Override
    public boolean existsByIdNumber(String idNumber) {
        return studentRepository.existsByIdNumber(idNumber);
    }

    @Override
    public boolean existsByPhoneNumber(String phoneNumber) {
        return studentRepository.existsByPhoneNumber(phoneNumber);
    }

    private String generateStudentCode(String yearCode) {
        String prefix = "SV" + yearCode;
        String suffix;
        do {
            suffix = String.format("%04d", new Random().nextInt(10000));
        } while (studentRepository.existsByStudentCode(prefix + suffix));
        return prefix + suffix;
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