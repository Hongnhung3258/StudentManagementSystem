package com.university.management.controller;

import com.university.management.entity.Department;
import com.university.management.service.DepartmentService;
import com.university.management.service.LecturerService;
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
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private LecturerService lecturerService;

    @GetMapping("/departments")
    public String listDepartments(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Department> departments = departmentService.searchDepartments(keyword, pageable);
        model.addAttribute("departments", departments);
        model.addAttribute("keyword", keyword);
        model.addAttribute("pageTitle", "Quản lý khoa");
        if (departments.isEmpty() && keyword != null && !keyword.isEmpty()) {
            model.addAttribute("errorMessage", "Không tìm thấy khoa phù hợp với từ khóa: " + keyword);
        }
        return "admin/department/management";
    }

    @GetMapping("/departments/add")
    public String showAddDepartmentForm(Model model) {
        model.addAttribute("department", new Department());
        model.addAttribute("lecturers", lecturerService.findAll());
        model.addAttribute("pageTitle", "Tạo khoa mới");
        return "admin/department/add";
    }

    @GetMapping("/departments/edit/{id}")
    public String showEditDepartmentForm(@PathVariable Integer id, Model model) {
        Department department = departmentService.findById(id);
        if (department == null) {
            model.addAttribute("errorMessage", "Khoa không tồn tại");
            return "redirect:/departments";
        }
        model.addAttribute("department", department);
        model.addAttribute("lecturers", lecturerService.findAll());
        model.addAttribute("pageTitle", "Chỉnh sửa khoa");
        return "admin/department/add";
    }

    @PostMapping("/departments/add")
    public String saveDepartment(@Valid @ModelAttribute("department") Department department,
                                 BindingResult result, Model model) {
        // Check validation errors from annotations
        if (result.hasErrors()) {
            model.addAttribute("lecturers", lecturerService.findAll());
            model.addAttribute("pageTitle", department.getId() != null ? "Chỉnh sửa khoa" : "Tạo khoa mới");
            return "admin/department/add";
        }

        try {
            // Validate department code uniqueness
            if (department.getDepartmentCode() != null && !department.getDepartmentCode().isEmpty()) {
                if (department.getId() == null && departmentService.existsByDepartmentCode(department.getDepartmentCode())) {
                    result.rejectValue("departmentCode", "error.department", "Mã khoa đã tồn tại");
                } else if (department.getId() != null) {
                    Department existing = departmentService.findById(department.getId());
                    if (!existing.getDepartmentCode().equals(department.getDepartmentCode()) &&
                            departmentService.existsByDepartmentCode(department.getDepartmentCode())) {
                        result.rejectValue("departmentCode", "error.department", "Mã khoa đã tồn tại");
                    }
                }
            }

            // Validate department name uniqueness
            if (department.getId() == null) {
                if (departmentService.existsByDepartmentName(department.getDepartmentName())) {
                    result.rejectValue("departmentName", "error.department", "Tên khoa đã tồn tại");
                }
            } else {
                Department existing = departmentService.findById(department.getId());
                if (!existing.getDepartmentName().equals(department.getDepartmentName()) &&
                        departmentService.existsByDepartmentName(department.getDepartmentName())) {
                    result.rejectValue("departmentName", "error.department", "Tên khoa đã tồn tại");
                }
            }

            // Validate head lecturer code
            if (department.getHeadLecturerCode() != null && !department.getHeadLecturerCode().isEmpty()) {
                if (!departmentService.isValidHeadLecturer(department.getHeadLecturerCode())) {
                    result.rejectValue("headLecturerCode", "error.department", "Mã trưởng khoa không tồn tại");
                }
            }

            if (result.hasErrors()) {
                model.addAttribute("lecturers", lecturerService.findAll());
                model.addAttribute("pageTitle", department.getId() != null ? "Chỉnh sửa khoa" : "Tạo khoa mới");
                return "admin/department/add";
            }

            departmentService.saveDepartment(department);
            model.addAttribute("successMessage", "Lưu khoa thành công");
            model.addAttribute("department", new Department()); // Reset form
            model.addAttribute("lecturers", lecturerService.findAll());
            model.addAttribute("pageTitle", "Tạo khoa mới");
            return "admin/department/add"; // Stay on add page
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi lưu khoa: " + e.getMessage());
            model.addAttribute("lecturers", lecturerService.findAll());
            model.addAttribute("pageTitle", department.getId() != null ? "Chỉnh sửa khoa" : "Tạo khoa mới");
            return "admin/department/add";
        }
    }

    @PostMapping("/departments/delete/{id}")
    public String deleteDepartment(@PathVariable Integer id, Model model) {
        try {
            departmentService.deleteDepartment(id);
            model.addAttribute("successMessage", "Xóa khoa thành công");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi xóa khoa: " + e.getMessage());
        }
        return "redirect:/departments";
    }
}