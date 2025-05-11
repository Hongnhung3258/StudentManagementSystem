package com.example.controller;

import com.example.entity.Course;
import com.example.service.CourseService;
import com.example.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controller for managing course-related operations.
 */
@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private DepartmentService departmentService;

    // Display course list with search and pagination
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
        return "courses";
    }

    // Show form for adding a new course
    @GetMapping("/courses/add")
    public String showAddCourseForm(Model model) {
        model.addAttribute("course", new Course());
        model.addAttribute("departments", departmentService.findAll());
        model.addAttribute("pageTitle", "Thêm Môn học mới");
        return "create-course";
    }

    // Show form for editing an existing course
    @GetMapping("/courses/edit/{id}")
    public String showEditCourseForm(@PathVariable Integer id, Model model) {
        Course course = courseService.findById(id);
        if (course == null) {
            model.addAttribute("errorMessage", "Môn học không tồn tại");
            return "redirect:/courses";
        }
        model.addAttribute("course", course);
        model.addAttribute("departments", departmentService.findAll());
        model.addAttribute("pageTitle", "Chỉnh sửa Môn học");
        return "create-course";
    }

    // Handle saving a new or updated course
    @PostMapping("/courses/add")
    public String saveCourse(@Valid @ModelAttribute("course") Course course,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("departments", departmentService.findAll());
            model.addAttribute("pageTitle", course.getId() != null ? "Chỉnh sửa Môn học" : "Thêm Môn học mới");
            return "create-course";
        }

        try {
            // Validate uniqueness
            if (course.getId() == null) { // New course
                if (courseService.existsByCourseCode(course.getCourseCode())) {
                    result.rejectValue("courseCode", "error.course", "Mã môn đã tồn tại");
                }
                if (courseService.existsByCourseName(course.getCourseName())) {
                    result.rejectValue("courseName", "error.course", "Tên môn đã tồn tại");
                }
            } else {
                Course existing = courseService.findById(course.getId());
                if (!existing.getCourseCode().equals(course.getCourseCode()) &&
                    courseService.existsByCourseCode(course.getCourseCode())) {
                    result.rejectValue("courseCode", "error.course", "Mã môn đã tồn tại");
                }
                if (!existing.getCourseName().equals(course.getCourseName()) &&
                    courseService.existsByCourseName(course.getCourseName())) {
                    result.rejectValue("courseName", "error.course", "Tên môn đã tồn tại");
                }
            }

            if (result.hasErrors()) {
                model.addAttribute("departments", departmentService.findAll());
                model.addAttribute("pageTitle", course.getId() != null ? "Chỉnh sửa Môn học" : "Thêm Môn học mới");
                return "create-course";
            }

            courseService.saveCourse(course);
            model.addAttribute("successMessage", "Lưu môn học thành công");
            return "redirect:/courses";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi lưu môn học: " + e.getMessage());
            model.addAttribute("departments", departmentService.findAll());
            model.addAttribute("pageTitle", course.getId() != null ? "Chỉnh sửa Môn học" : "Thêm Môn học mới");
            return "create-course";
        }
    }

    // Handle deleting a course
    @PostMapping("/courses/delete/{id}")
    public String deleteCourse(@PathVariable Integer id, Model model) {
        try {
            courseService.deleteCourse(id);
            model.addAttribute("successMessage", "Xóa môn học thành công");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi xóa môn học: " + e.getMessage());
        }
        return "redirect:/courses";
    }
}