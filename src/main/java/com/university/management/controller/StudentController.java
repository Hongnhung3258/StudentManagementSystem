package com.example.controller;

import com.example.entity.Student;
import com.example.service.DepartmentService;
import com.example.service.StudentService;
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
 * Controller for managing student-related operations.
 */
@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private DepartmentService departmentService;

    // Display student list with search and pagination
    @GetMapping("/students")
    public String listStudents(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Student> students = studentService.searchStudents(keyword, pageable);
        model.addAttribute("students", students);
        model.addAttribute("keyword", keyword);
        return "students";
    }

    // Show form for adding a new student
    @GetMapping("/students/add")
    public String showAddStudentForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("departments", departmentService.findAll());
        model.addAttribute("pageTitle", "Thêm Sinh viên mới");
        return "create-student";
    }

    // Show form for editing an existing student
    @GetMapping("/students/edit/{id}")
    public String showEditStudentForm(@PathVariable Integer id, Model model) {
        Student student = studentService.findById(id);
        if (student == null) {
            model.addAttribute("errorMessage", "Sinh viên không tồn tại");
            return "redirect:/students";
        }
        model.addAttribute("student", student);
        model.addAttribute("departments", departmentService.findAll());
        model.addAttribute("pageTitle", "Chỉnh sửa Sinh viên");
        return "create-student";
    }

    // Handle saving a new or updated student
    @PostMapping("/students/add")
    public String saveStudent(@Valid @ModelAttribute("student") Student student,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("departments", departmentService.findAll());
            model.addAttribute("pageTitle", student.getId() != null ? "Chỉnh sửa Sinh viên" : "Thêm Sinh viên mới");
            return "create-student";
        }

        try {
            // Validate uniqueness
            if (student.getId() == null) { // New student
                if (studentService.existsByStudentCode(student.getStudentCode())) {
                    result.rejectValue("studentCode", "error.student", "Mã sinh viên đã tồn tại");
                }
                if (studentService.existsByIdNumber(student.getIdNumber())) {
                    result.rejectValue("idNumber", "error.student", "Số CCCD đã tồn tại");
                }
                if (student.getPhoneNumber() != null && !student.getPhoneNumber().isEmpty() &&
                    studentService.existsByPhoneNumber(student.getPhoneNumber())) {
                    result.rejectValue("phoneNumber", "error.student", "Số điện thoại đã tồn tại");
                }
            }

            if (result.hasErrors()) {
                model.addAttribute("departments", departmentService.findAll());
                model.addAttribute("pageTitle", student.getId() != null ? "Chỉnh sửa Sinh viên" : "Thêm Sinh viên mới");
                return "create-student";
            }

            studentService.saveStudent(student);
            model.addAttribute("successMessage", "Lưu sinh viên thành công");
            return "redirect:/students";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi lưu sinh viên: " + e.getMessage());
            model.addAttribute("departments", departmentService.findAll());
            model.addAttribute("pageTitle", student.getId() != null ? "Chỉnh sửa Sinh viên" : "Thêm Sinh viên mới");
            return "create-student";
        }
    }

    // Handle deleting a student
    @PostMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable Integer id, Model model) {
        try {
            studentService.deleteStudent(id);
            model.addAttribute("successMessage", "Xóa sinh viên thành công");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi xóa sinh viên: " + e.getMessage());
        }
        return "redirect:/students";
    }
}