package com.university.management.controller;

import com.university.management.entity.Schedule;
import com.university.management.service.ActivityLogService;
import com.university.management.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/newschedule")
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final ActivityLogService activityLogService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService, ActivityLogService activityLogService) {
        this.scheduleService = scheduleService;
        this.activityLogService = activityLogService;
    }

    @GetMapping
    public String showScheduleSearch(Model model) {
        // Get current year for academic year options
        int currentYear = LocalDate.now().getYear();
        List<String> academicYears = new ArrayList<>();
        
        // Generate 5 academic years (2 previous, current, 2 future)
        for (int i = -2; i <= 2; i++) {
            int startYear = currentYear + i;
            String academicYear = startYear + "-" + (startYear + 1);
            academicYears.add(academicYear);
        }
        
        model.addAttribute("academicYears", academicYears);
        model.addAttribute("semesters", Arrays.asList(1, 2, 3, 4));
        model.addAttribute("pageTitle", "Lịch trình học");
        
        // Empty schedule to show blank timetable initially
        model.addAttribute("timetable", new String[7][12]);
        model.addAttribute("daysOfWeek", Arrays.asList("Thứ hai", "Thứ ba", "Thứ tư", "Thứ năm", "Thứ sáu", "Thứ bảy", "Chủ nhật"));
        model.addAttribute("periods", Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));
        
        return "schedule/newformschedule";
    }

    @GetMapping("/management")
    public String searchSchedules(
            @RequestParam("academicYear") String academicYear,
            @RequestParam("semester") Integer semester,
            @RequestParam(value = "classId", required = false) String classId,
            Model model) {
        
        List<Schedule> schedules;
        if (classId != null && !classId.trim().isEmpty()) {
            schedules = scheduleService.findSchedulesByClassAndSemester(classId, academicYear, semester);
            model.addAttribute("classId", classId);
        } else {
            schedules = scheduleService.findSchedulesBySemester(academicYear, semester);
        }
        
        // Create a timetable grid locally instead of using a service method
        String[][] timetable = createTimetableGrid(schedules);
        
        // Get current year for academic year options
        int currentYear = LocalDate.now().getYear();
        List<String> academicYears = new ArrayList<>();
        
        // Generate 5 academic years (2 previous, current, 2 future)
        for (int i = -2; i <= 2; i++) {
            int startYear = currentYear + i;
            String year = startYear + "-" + (startYear + 1);
            academicYears.add(year);
        }
        
        model.addAttribute("academicYears", academicYears);
        model.addAttribute("semesters", Arrays.asList(1, 2, 3, 4));
        model.addAttribute("selectedAcademicYear", academicYear);
        model.addAttribute("selectedSemester", semester);
        model.addAttribute("timetable", timetable);
        model.addAttribute("daysOfWeek", Arrays.asList("Thứ hai", "Thứ ba", "Thứ tư", "Thứ năm", "Thứ sáu", "Thứ bảy", "Chủ nhật"));
        model.addAttribute("periods", Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));
        model.addAttribute("schedules", schedules);
        model.addAttribute("pageTitle", "Lịch trình học");
        
        return "schedule/management";
    }
    
    // Helper method to create a timetable grid from schedules
    private String[][] createTimetableGrid(List<Schedule> schedules) {
        // Create a 7x12 grid (7 days, 12 periods per day) - empty initially
        String[][] grid = new String[7][12];
        
        // Initialize all cells to empty
        for (int i = 0; i < 7; i++) {
            Arrays.fill(grid[i], "");
        }
        
        // Days of week mapping
        String[] daysOfWeek = {"Thứ hai", "Thứ ba", "Thứ tư", "Thứ năm", "Thứ sáu", "Thứ bảy", "Chủ nhật"};
        
        // Fill the grid with schedule information
        for (Schedule schedule : schedules) {
            int dayIndex = getDayIndex(schedule.getDayOfWeek().getVietnameseName(), daysOfWeek);
            if (dayIndex >= 0 && dayIndex < 7) {
                int startPeriod = schedule.getStartPeriod() - 1; // 0-based index
                int endPeriod = schedule.getEndPeriod() - 1;     // 0-based index
                
                if (startPeriod >= 0 && endPeriod < 12) {
                    // Fill all periods for this schedule
                    for (int period = startPeriod; period <= endPeriod; period++) {
                        grid[dayIndex][period] = schedule.getClassId() + " - " + schedule.getRoom();
                    }
                }
            }
        }
        
        return grid;
    }
    
    private int getDayIndex(String dayOfWeek, String[] daysOfWeek) {
        for (int i = 0; i < daysOfWeek.length; i++) {
            if (daysOfWeek[i].equals(dayOfWeek)) {
                return i;
            }
        }
        return -1; // Not found
    }

    @GetMapping("/create")
    public String showCreateScheduleForm(Model model) {
        model.addAttribute("schedule", new Schedule());
        model.addAttribute("daysOfWeek", Arrays.asList("Thứ hai", "Thứ ba", "Thứ tư", "Thứ năm", "Thứ sáu", "Thứ bảy", "Chủ nhật"));
        model.addAttribute("periods", Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));
        
        // Get current year for academic year options
        int currentYear = LocalDate.now().getYear();
        List<String> academicYears = new ArrayList<>();
        
        // Generate 5 academic years (2 previous, current, 2 future)
        for (int i = -2; i <= 2; i++) {
            int startYear = currentYear + i;
            String academicYear = startYear + "-" + (startYear + 1);
            academicYears.add(academicYear);
        }
        
        model.addAttribute("academicYears", academicYears);
        model.addAttribute("semesters", Arrays.asList(1, 2, 3, 4));
        model.addAttribute("pageTitle", "Tạo lịch học mới");
        
        return "schedule/form";
    }

    @PostMapping("/save")
    public String saveSchedule(
            @ModelAttribute Schedule schedule,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Principal principal) {
        
        if (result.hasErrors()) {
            return "schedule/form";
        }
        
        try {
            Schedule savedSchedule = scheduleService.saveSchedule(schedule);
            
            // Log the activity
            activityLogService.logActivity(
                principal.getName(), 
                "Thêm", 
                "Lịch học", 
                savedSchedule.getId()
            );
            
            redirectAttributes.addFlashAttribute("successMessage", "Đã lưu lịch học thành công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi: " + e.getMessage());
        }
        
        return "redirect:/schedules";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Schedule schedule = scheduleService.getScheduleById(id);
        model.addAttribute("schedule", schedule);
        model.addAttribute("daysOfWeek", Arrays.asList("Thứ hai", "Thứ ba", "Thứ tư", "Thứ năm", "Thứ sáu", "Thứ bảy", "Chủ nhật"));
        model.addAttribute("periods", Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));
        
        // Get current year for academic year options
        int currentYear = LocalDate.now().getYear();
        List<String> academicYears = new ArrayList<>();
        
        // Generate 5 academic years (2 previous, current, 2 future)
        for (int i = -2; i <= 2; i++) {
            int startYear = currentYear + i;
            String academicYear = startYear + "-" + (startYear + 1);
            academicYears.add(academicYear);
        }
        
        model.addAttribute("academicYears", academicYears);
        model.addAttribute("semesters", Arrays.asList(1, 2, 3, 4));
        model.addAttribute("pageTitle", "Chỉnh sửa lịch học");
        
        return "schedule/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteSchedule(
            @PathVariable("id") Integer id,
            RedirectAttributes redirectAttributes,
            Principal principal) {
        
        try {
            scheduleService.deleteSchedule(id);
            
            // Log the activity
            activityLogService.logActivity(
                principal.getName(), 
                "Xóa", 
                "Lịch học", 
                id
            );
            
            redirectAttributes.addFlashAttribute("successMessage", "Đã xóa lịch học thành công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi: " + e.getMessage());
        }
        
        return "redirect:/schedules";
    }
}