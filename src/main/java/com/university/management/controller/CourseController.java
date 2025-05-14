package com.university.management.controller;

import com.example.entity.Course;
import com.example.service.CourseService;
import com.example.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/courses")
    public String listCourses(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Course> courses = courseService.searchCourses(keyword, pageable);
        model.addAttribute("courses", courses);
        model.addAttribute("keyword", keyword);
        if (courses.isEmpty() && keyword != null && !keyword.isEmpty()) {
            model.addAttribute("errorMessage", "Không tìm thấy môn học phù hợp với từ khóa: " + keyword);
        }
        return "courses";
    }

    @GetMapping("/courses/add")
    public String showAddCourseForm(Model model) {
        model.addAttribute("course", new Course());
        model.addAttribute("departments", departmentService.findAll());
        model.addAttribute("pageTitle", "Thêm Môn học mới");
        return "course/add";
    }

    @GetMapping("/courses/edit/{id}")
    public String showEditCourseForm(@PathVariable Long id, Model model) {
        Course course = courseService.findById(id);
        if (course == null) {
            model.addAttribute("errorMessage", "Môn học không tồn tại");
            return "redirect:/courses";
        }
        model.addAttribute("course", course);
        model.addAttribute("departments", departmentService.findAll());
        model.addAttribute("pageTitle", "Chỉnh sửa Môn học");
        return "course/add";
    }

    @PostMapping("/courses/add")
    public String saveCourse(@Valid @ModelAttribute("course") Course course,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("departments", departmentService.findAll());
            model.addAttribute("pageTitle", course.getId() != null ? "Chỉnh sửa Môn học" : "Thêm Môn học mới");
            return "course/add";
        }

        try {
            // Validate courseCode uniqueness
            if (course.getCourseCode() != null && !course.getCourseCode().isEmpty()) {
                if (course.getId() == null && courseService.existsByCourseCode(course.getCourseCode())) {
                    result.rejectValue("courseCode", "error.course", "Mã môn đã tồn tại");
                } else if (course.getId() != null) {
                    Course existing = courseService.findById(course.getId());
                    if (!existing.getCourseCode().equals(course.getCourseCode()) &&
                            courseService.existsByCourseCode(course.getCourseCode())) {
                        result.rejectValue("courseCode", "error.course", "Mã môn đã tồn tại");
                    }
                }
            }

            // Validate courseCode format
            if (course.getCourseCode() != null && !course.getCourseCode().isEmpty() &&
                    !course.getCourseCode().matches("MH\\d{4}")) {
                result.rejectValue("courseCode", "error.course", "Mã môn phải có định dạng MH + 4 số (ví dụ: MH0001)");
            }

            if (result.hasErrors()) {
                model.addAttribute("departments", departmentService.findAll());
                model.addAttribute("pageTitle", course.getId() != null ? "Chỉnh sửa Môn học" : "Thêm Môn học mới");
                return "course/add";
            }

            courseService.saveCourse(course);
            model.addAttribute("successMessage", "Lưu môn học thành công");
            return "redirect:/courses";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi lưu môn học: " + e.getMessage());
            model.addAttribute("departments", departmentService.findAll());
            model.addAttribute("pageTitle", course.getId() != null ? "Chỉnh sửa Môn học" : "Thêm Môn học mới");
            return "course/add";
        }
    }

    @PostMapping("/courses/delete/{id}")
    public String deleteCourse(@PathVariable Long id, Model model) {
        try {
            courseService.deleteCourse(id);
            model.addAttribute("successMessage", "Xóa môn học thành công");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi xóa môn học: " + e.getMessage());
        }
        return "redirect:/courses";
    }
}