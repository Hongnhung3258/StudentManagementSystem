package com.university.management.repository;

import com.university.management.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    List<Schedule> findByClassIdAndAcademicYearAndSemester(String classId, String academicYear, Integer semester);
    List<Schedule> findByAcademicYearAndSemester(String academicYear, Integer semester);
}