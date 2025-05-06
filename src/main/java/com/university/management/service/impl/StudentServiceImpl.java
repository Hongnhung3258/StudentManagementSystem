package com.university.management.service.impl;

import com.university.management.entity.Student;
import com.university.management.repository.StudentRepository;
import com.university.management.service.StudentService;
import com.university.management.util.CodeGenerator;
import com.university.management.util.PasswordGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    
    private final StudentRepository studentRepository;
    private final CodeGenerator codeGenerator;
    private final PasswordGenerator passwordGenerator;

    @Override
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(Integer id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Student findByStudentCode(String studentCode) {
        return studentRepository.findByStudentCode(studentCode).orElse(null);
    }

    @Override
    public List<Student> findByDepartmentName(String departmentName) {
        return studentRepository.findByDepartmentName(departmentName);
    }

    @Override
    @Transactional
    public Student saveStudent(Student student) {
        if (student.getStudentCode() == null || student.getStudentCode().isEmpty()) {
            student.setStudentCode(generateStudentCode(student.getYearCode()));
        }
        if (student.getPassword() == null || student.getPassword().isEmpty()) {
            student.setPassword(generatePassword());
        }
        return studentRepository.save(student);
    }

    @Override
    @Transactional
    public void deleteStudent(Integer id) {
        studentRepository.deleteById(id);
    }

    @Override
    public String generateStudentCode(String yearCode) {
        return codeGenerator.generateStudentCode(yearCode);
    }

    @Override
    public String generatePassword() {
        return passwordGenerator.generateRandomPassword();
    }

    @Override
    public boolean isStudentCodeExists(String code) {
        return studentRepository.existsByStudentCode(code);
    }

    @Override
    public boolean isIdNumberExists(String idNumber) {
        return studentRepository.existsByIdNumber(idNumber);
    }

    @Override
    public Long countStudents() {
        return studentRepository.countStudents();
    }
}
