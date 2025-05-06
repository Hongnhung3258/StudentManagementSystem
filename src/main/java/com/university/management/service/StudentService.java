package com.university.management.service;

import com.university.management.entity.Student;
import java.util.List;

public interface StudentService {
    List<Student> findAllStudents();
    Student findById(Integer id);
    Student findByStudentCode(String studentCode);
    List<Student> findByDepartmentName(String departmentName);
    Student saveStudent(Student student);
    void deleteStudent(Integer id);
    String generateStudentCode(String yearCode);
    String generatePassword();
    boolean isStudentCodeExists(String code);
    boolean isIdNumberExists(String idNumber);
    Long countStudents();
}
