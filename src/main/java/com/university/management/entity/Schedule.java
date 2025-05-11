package com.example.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

/**
 * Entity mapping to the schedule table in the database.
 */
@Entity
@Table(name = "schedule", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"class_code", "start_period", "weekday", "academic_year", "semester"}),
    @UniqueConstraint(columnNames = {"classroom", "start_period", "weekday", "academic_year", "semester"}),
    @UniqueConstraint(columnNames = {"lecturer_id", "start_period", "weekday", "academic_year", "semester"})
})
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "class_code", nullable = false, length = 5)
    @NotBlank(message = "Mã lớp là bắt buộc")
    @Size(min = 5, max = 5, message = "Mã lớp phải có 5 ký tự")
    private String classId;

    @Column(name = "lecturer_id", nullable = false, length = 6)
    @NotBlank(message = "Mã giảng viên là bắt buộc")
    @Size(min = 6, max = 6, message = "Mã giảng viên phải có 6 ký tự")
    private String lecturerId;

    @Column(name = "classroom", nullable = false, length = 20)
    @NotBlank(message = "Phòng học là bắt buộc")
    @Size(max = 20, message = "Phòng học tối đa 20 ký tự")
    private String classroom;

    @Column(name = "start_period", nullable = false)
    @Min(value = 1, message = "Tiết bắt đầu phải từ 1")
    @Max(value = 12, message = "Tiết bắt đầu tối đa 12")
    private Integer startPeriod;

    @Column(name = "end_period", nullable = false)
    @Min(value = 2, message = "Tiết kết thúc phải từ 2")
    @Max(value = 12, message = "Tiết kết thúc tối đa 12")
    private Integer endPeriod;

    @Column(name = "weekday", nullable = false)
    @NotBlank(message = "Thứ là bắt buộc")
    private String dayOfWeek;

    @Column(name = "start_date", nullable = false)
    @NotNull(message = "Ngày bắt đầu là bắt buộc")
    private LocalDate startDate;

    @Column(name = "academic_year", nullable = false, length = 9)
    @NotBlank(message = "Năm học là bắt buộc")
    @Pattern(regexp = "^[0-9]{4}-[0-9]{4}$", message = "Năm học phải có định dạng YYYY-YYYY")
    private String academicYear;

    @Column(name = "semester", nullable = false)
    @Min(value = 1, message = "Học kỳ phải từ 1")
    @Max(value = 4, message = "Học kỳ tối đa 4")
    private Integer semester;

    @Column(name = "schedule_summary", insertable = false, updatable = false)
    private String scheduleSummary;

    // Constructors
    public Schedule() {}

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(String lecturerId) {
        this.lecturerId = lecturerId;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public Integer getStartPeriod() {
        return startPeriod;
    }

    public void setStartPeriod(Integer startPeriod) {
        this.startPeriod = startPeriod;
    }

    public Integer getEndPeriod() {
        return endPeriod;
    }

    public void setEndPeriod(Integer endPeriod) {
        this.endPeriod = endPeriod;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public String getScheduleSummary() {
        return scheduleSummary;
    }

    public void setScheduleSummary(String scheduleSummary) {
        this.scheduleSummary = scheduleSummary;
    }
}