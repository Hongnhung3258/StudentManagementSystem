package com.university.management.service.impl;

import com.university.management.service.ClassService;
import com.university.management.service.DashboardService;
import com.university.management.service.LecturerService;
import com.university.management.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {
    
    private final StudentService studentService;
    private final LecturerService lecturerService;
    private final ClassService classService;

    @Override
    public Map<String, Long> getStatistics() {
        Map<String, Long> statistics = new HashMap<>();
        
        // Get counts from services
        statistics.put("studentCount", studentService.countStudents());
        statistics.put("lecturerCount", lecturerService.countLecturers());
        statistics.put("classCount", classService.countClasses());
        
        return statistics;
    }
}
