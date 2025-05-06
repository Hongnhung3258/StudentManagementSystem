package com.university.management.service;

import com.university.management.entity.Class;
import java.util.List;

public interface ClassService {
    List<Class> findAllClasses();
    Class findById(Integer id);
    Class findByClassCode(String classCode);
    List<Class> findByCourseName(String courseName);
    List<Class> findByDepartmentName(String departmentName);
    List<Class> findByLecturerCode(String lecturerCode);
    Class saveClass(Class classEntity);
    void deleteClass(Integer id);
    String generateClassCode();
    boolean isClassCodeExists(String code);
    Long countClasses();
}
