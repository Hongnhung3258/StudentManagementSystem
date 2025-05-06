package com.university.management.service;

import com.university.management.entity.Schedule;
import java.util.List;

public interface ScheduleService {
    List<Schedule> findAllSchedules();
    List<Schedule> findSchedulesByClassAndSemester(String classId, String academicYear, Integer semester);
    List<Schedule> findSchedulesBySemester(String academicYear, Integer semester);
    Schedule getScheduleById(Integer id);
    Schedule saveSchedule(Schedule schedule);
    void deleteSchedule(Integer id);
    
    /**
     * Creates a schedule timetable mapping for displaying in a grid
     * @param schedules The schedules to map
     * @return A 2D array representation of the timetable (days x periods)
     */
    String[][] createTimetableGrid(List<Schedule> schedules);
}