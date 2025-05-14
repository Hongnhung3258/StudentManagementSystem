package com.university.management.controller;

import com.example.entity.Student;
import com.example.service.DepartmentService;
import com.example.service.StudentService;
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
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private DepartmentService departmentService;

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

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        long studentCount = studentService.countAllStudents();
        model.addAttribute("studentCount", studentCount);
        return "dashboard";
    }

    @GetMapping("/students/add")
    public String showAddStudentForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("departments", departmentService.findAll());
        model.addAttribute("pageTitle", "Thêm Sinh viên mới");
        return "student/add";
    }

    @GetMapping("/students/edit/{id}")
    public String showEditStudentForm(@PathVariable Long id, Model model) {
        Student student = studentService.findById(id);
        if (student == null) {
            model.addAttribute("errorMessage", "Sinh viên không tồn tại");
            return "redirect:/students";
        }
        model.addAttribute("student", student);
        model.addAttribute("departments", departmentService.findAll());
        model.addAttribute("pageTitle", "Chỉnh sửa sinh viên");
        return "student/add";
    }

    @PostMapping("/students/add")
    public String saveStudent(@Valid @ModelAttribute("student") Student student,
                              BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("departments", departmentService.findAll());
            model.addAttribute("pageTitle", student.getId() != null ? "Chỉnh sửa sinh viên" : "Thêm Sinh viên mới");
            return "student/add";
        }

        try {
            // Validate uniqueness
            if (student.getId() == null) { // New student
                if (studentService.existsByNationalId(student.getNationalId())) {
                    result.rejectValue("nationalId", "error.student", "Số CCCD đã tồn tại");
                }
                if (studentService.existsByPhoneNumber(student.getPhoneNumber())) {
                    result.rejectValue("phoneNumber", "error.student", "Số điện thoại đã tồn tại");
                }
            } else {
                Student existing = studentService.findById(student.getId());
                if (!existing.getNationalId().equals(student.getNationalId()) &&
                        studentService.existsByNationalId(student.getNationalId())) {
                    result.rejectValue("nationalId", "error.student", "Số CCCD đã tồn tại");
                }
                if (!existing.getPhoneNumber().equals(student.getPhoneNumber()) &&
                        studentService.existsByPhoneNumber(student.getPhoneNumber())) {
                    result.rejectValue("phoneNumber", "error.student", "Số điện thoại đã tồn tại");
                }
            }

            // Validate phone number format
            if (!student.getPhoneNumber().matches("\\d{10,15}")) {
                result.rejectValue("phoneNumber", "error.student", "Số điện thoại phải có 10-15 chữ số");
            }

            // Validate nationalId format
            if (!student.getNationalId().matches("\\d{12}")) {
                result.rejectValue("nationalId", "error.student", "Số CCCD phải có 12 chữ số");
            }

            // Validate intake format
            if (!student.getIntake().matches("\\d{2}")) {
                result.rejectValue("intake", "error.student", "Mã năm nhập học phải có 2 chữ số");
            }

            if (result.hasErrors()) {
                model.addAttribute("departments", departmentService.findAll());
                model.addAttribute("pageTitle", student.getId() != null ? "Chỉnh sửa sinh viên" : "Thêm Sinh viên mới");
                return "student/add";
            }

            studentService.saveStudent(student);
            model.addAttribute("successMessage", "Lưu sinh viên thành công");
            return "redirect:/students";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi lưu sinh viên: " + e.getMessage());
            model.addAttribute("departments", departmentService.findAll());
            model.addAttribute("pageTitle", student.getId() != null ? "Chỉnh sửa sinh viên" : "Thêm Sinh viên mới");
            return "student/add";
        }
    }

    @PostMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable Long id, Model model) {
        try {
            studentService.deleteStudent(id);
            model.addAttribute("successMessage", "Xóa sinh viên thành công");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi xóa sinh viên: " + e.getMessage());
        }
        return "redirect:/students";
    }
}