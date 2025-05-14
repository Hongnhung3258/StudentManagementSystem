package com.university.management.service.impl;

import com.example.entity.Class;
import com.example.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassRepository classRepository;

    @Override
    public List<Class> findAll() {
        return classRepository.findAll();
    }

    @Override
    public Class findById(Integer id) {
        return classRepository.findById(id).orElse(null);
    }

    @Override
    public void saveClass(Class classEntity) {
        classRepository.save(classEntity);
    }

    @Override
    public void deleteClass(Integer id) {
        classRepository.deleteById(id);
    }

    @Override
    public long countClasses() {
        return classRepository.count();
    }

    @Override
    public String findMaxClassCode() {
        return classRepository.findMaxClassCode();
    }

    @Override
    public Page<Class> findAllPaged(Pageable pageable) {
        return classRepository.findAll(pageable);
    }
}