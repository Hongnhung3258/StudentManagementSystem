package com.university.management.service;

import com.university.management.entity.Lecturer;
import java.util.List;

public interface LecturerService {
    List<Lecturer> findAllLecturers();
    Lecturer findById(Integer id);
    Lecturer findByLecturerCode(String lecturerCode);
    List<Lecturer> findByDepartmentName(String departmentName);
    Lecturer saveLecturer(Lecturer lecturer);
    void deleteLecturer(Integer id);
    String generateLecturerCode();
    String generatePassword();
    boolean isLecturerCodeExists(String code);
    boolean isIdNumberExists(String idNumber);
    Long countLecturers();
}
