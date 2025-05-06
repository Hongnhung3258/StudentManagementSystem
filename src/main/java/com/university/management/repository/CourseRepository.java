package com.university.management.repository;

import com.university.management.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    Optional<Course> findByCourseCode(String courseCode);
    Optional<Course> findByCourseName(String courseName);
    List<Course> findByDepartmentName(String departmentName);
    boolean existsByCourseCode(String courseCode);
    boolean existsByCourseName(String courseName);
    
    @Query(value = "SELECT MAX(CAST(SUBSTRING(mamon, 3, 3) AS UNSIGNED)) FROM course", nativeQuery = true)
    Integer findMaxCourseNumber();
}
