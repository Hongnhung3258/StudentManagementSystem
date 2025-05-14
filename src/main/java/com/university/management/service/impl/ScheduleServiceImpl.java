package com.university.management.service.impl;

import com.university.management.entity.Schedule;
import com.university.management.repository.ScheduleRepository;
import com.university.management.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Arrays;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public List<Schedule> findAllSchedules() {
        return scheduleRepository.findAll();
    }

    @Override
    public List<Schedule> findSchedulesByClassAndSemester(String classId, String academicYear, Integer semester) {
        return scheduleRepository.findByClassIdAndAcademicYearAndSemester(classId, academicYear, semester);
    }

    @Override
    public List<Schedule> findSchedulesBySemester(String academicYear, Integer semester) {
        return scheduleRepository.findByAcademicYearAndSemester(academicYear, semester);
    }

    @Override
    public Schedule getScheduleById(Integer id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy lịch học với ID: " + id));
    }

    @Override
    public Schedule saveSchedule(Schedule schedule) {
        // Ensure the schedule description is updated
        schedule.updateScheduleDescription();
        return scheduleRepository.save(schedule);
    }

    @Override
    public void deleteSchedule(Integer id) {
        scheduleRepository.deleteById(id);
    }
    
    @Override
    public boolean checkScheduleConflict(Schedule schedule) {
        // Check for conflicts with other schedules in the same academic year and semester
        List<Schedule> semesterSchedules = scheduleRepository.findByAcademicYearAndSemester(
                schedule.getAcademicYear(), schedule.getSemester());
        
        for (Schedule existingSchedule : semesterSchedules) {
            // Skip self when updating
            if (schedule.getId() != null && schedule.getId().equals(existingSchedule.getId())) {
                continue;
            }
            
            // Check for room conflicts
            if (existingSchedule.getDayOfWeek().equals(schedule.getDayOfWeek()) && 
                existingSchedule.getRoom().equals(schedule.getRoom())) {
                
                // Check for period overlap
                if (periodsOverlap(
                        existingSchedule.getStartPeriod(), existingSchedule.getEndPeriod(),
                        schedule.getStartPeriod(), schedule.getEndPeriod())) {
                    return true; // Conflict found
                }
            }
            
            // Check for lecturer conflicts
            if (existingSchedule.getDayOfWeek().equals(schedule.getDayOfWeek()) && 
                existingSchedule.getLecturerId().equals(schedule.getLecturerId())) {
                
                // Check for period overlap
                if (periodsOverlap(
                        existingSchedule.getStartPeriod(), existingSchedule.getEndPeriod(),
                        schedule.getStartPeriod(), schedule.getEndPeriod())) {
                    return true; // Conflict found
                }
            }
        }
        
        return false; // No conflicts
    }
    
    private boolean periodsOverlap(int start1, int end1, int start2, int end2) {
        // Check if two time periods overlap
        return Math.max(start1, start2) <= Math.min(end1, end2);
    }
}