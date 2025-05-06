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
    
    private static final String[] DAYS_OF_WEEK = {
        "Thứ hai", "Thứ ba", "Thứ tư", "Thứ năm", "Thứ sáu", "Thứ bảy", "Chủ nhật"
    };

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
        // Generate the schedule display text if not set
        if (schedule.getScheduleDisplay() == null || schedule.getScheduleDisplay().isEmpty()) {
            schedule.setScheduleDisplay(schedule.getDayOfWeek() + " " + 
                                       schedule.getStartPeriod() + "-" + 
                                       schedule.getEndPeriod());
        }
        return scheduleRepository.save(schedule);
    }

    @Override
    public void deleteSchedule(Integer id) {
        scheduleRepository.deleteById(id);
    }
    
    @Override
    public String[][] createTimetableGrid(List<Schedule> schedules) {
        // Create a 7x12 grid (7 days, 12 periods per day) - empty initially
        String[][] grid = new String[7][12];
        
        // Initialize all cells to empty
        for (int i = 0; i < 7; i++) {
            Arrays.fill(grid[i], "");
        }
        
        // Fill the grid with schedule information
        for (Schedule schedule : schedules) {
            int dayIndex = getDayIndex(schedule.getDayOfWeek());
            if (dayIndex >= 0 && dayIndex < 7) {
                int startPeriod = schedule.getStartPeriod() - 1; // 0-based index
                int endPeriod = schedule.getEndPeriod() - 1;     // 0-based index
                
                if (startPeriod >= 0 && endPeriod < 12) {
                    // Fill all periods for this schedule
                    for (int period = startPeriod; period <= endPeriod; period++) {
                        grid[dayIndex][period] = schedule.getClassId() + " - " + schedule.getClassroom();
                    }
                }
            }
        }
        
        return grid;
    }
    
    private int getDayIndex(String dayOfWeek) {
        for (int i = 0; i < DAYS_OF_WEEK.length; i++) {
            if (DAYS_OF_WEEK[i].equals(dayOfWeek)) {
                return i;
            }
        }
        return -1; // Not found
    }
}