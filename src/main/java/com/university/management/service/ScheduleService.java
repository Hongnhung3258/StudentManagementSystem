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
    boolean checkScheduleConflict(Schedule schedule);
}