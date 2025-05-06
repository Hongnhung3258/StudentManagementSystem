package com.university.management.service.impl;

import com.university.management.entity.Class;
import com.university.management.repository.ClassRepository;
import com.university.management.service.ClassService;
import com.university.management.util.CodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassServiceImpl implements ClassService {
    
    private final ClassRepository classRepository;
    private final CodeGenerator codeGenerator;

    @Override
    public List<Class> findAllClasses() {
        return classRepository.findAll();
    }

    @Override
    public Class findById(Integer id) {
        return classRepository.findById(id).orElse(null);
    }

    @Override
    public Class findByClassCode(String classCode) {
        return classRepository.findByClassCode(classCode).orElse(null);
    }

    @Override
    public List<Class> findByCourseName(String courseName) {
        return classRepository.findByCourseName(courseName);
    }

    @Override
    public List<Class> findByDepartmentName(String departmentName) {
        return classRepository.findByDepartmentName(departmentName);
    }

    @Override
    public List<Class> findByLecturerCode(String lecturerCode) {
        return classRepository.findByLecturerCode(lecturerCode);
    }

    @Override
    @Transactional
    public Class saveClass(Class classEntity) {
        if (classEntity.getClassCode() == null || classEntity.getClassCode().isEmpty()) {
            classEntity.setClassCode(generateClassCode());
        }
        return classRepository.save(classEntity);
    }

    @Override
    @Transactional
    public void deleteClass(Integer id) {
        classRepository.deleteById(id);
    }

    @Override
    public String generateClassCode() {
        return codeGenerator.generateClassCode();
    }

    @Override
    public boolean isClassCodeExists(String code) {
        return classRepository.existsByClassCode(code);
    }

    @Override
    public Long countClasses() {
        return classRepository.countClasses();
    }
}
