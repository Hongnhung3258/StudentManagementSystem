package com.example.controller;

import com.example.entity.Department;
import com.example.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.DecimalFormat;

@Controller
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    // Hiển thị danh sách khoa
    @GetMapping("/departments")
    public String listDepartments(Model model) {
        model.addAttribute("departments", departmentService.findAll());
        return "departments";
    }

    // Hiển thị biểu mẫu tạo khoa mới
    @GetMapping("/departments/add")
    public String showAddDepartmentForm(Model model) {
        model.addAttribute("department", new Department());
        model.addAttribute("pageTitle", "Tạo khoa mới");
        return "create-department";
    }

    // Hiển thị biểu mẫu chỉnh sửa khoa
    @GetMapping("/departments/edit/{departmentName}")
    public String showEditDepartmentForm(@PathVariable String departmentName, Model model) {
        Department department = departmentService.findById(departmentName);
        if (department == null) {
            model.addAttribute("errorMessage", "Khoa không tồn tại");
            return "redirect:/departments";
        }
        model.addAttribute("department", department);
        model.addAttribute("pageTitle", "Chỉnh sửa khoa");
        return "create-department";
    }

    // Xử lý tạo hoặc cập nhật khoa
    @PostMapping("/departments/add")
    public String saveDepartment(@Valid @ModelAttribute("department") Department department,
                                 BindingResult result, Model model) {
        // Kiểm tra lỗi xác thực
        if (result.hasErrors()) {
            model.addAttribute("pageTitle", department.getDepartmentName() != null ? "Chỉnh sửa khoa" : "Tạo khoa mới");
            return "create-department";
        }

        // Xác thực headLecturerCode
        if (!departmentService.isValidHeadLecturerCode(department.getHeadLecturerCode())) {
            result.rejectValue("headLecturerCode", "error.department", "Mã trưởng khoa không hợp lệ");
            model.addAttribute("pageTitle", department.getDepartmentName() != null ? "Chỉnh sửa khoa" : "Tạo khoa mới");
            return "create-department";
        }

        // Tự động tạo mã khoa nếu trống
        if (department.getDepartmentCode() == null || department.getDepartmentCode().trim().isEmpty()) {
            String maxCode = departmentService.findMaxDepartmentCode();
            int nextCode = 1;
            if (maxCode != null && maxCode.matches("\\d+")) {
                nextCode = Integer.parseInt(maxCode) + 1;
            }
            department.setDepartmentCode(new DecimalFormat("0000").format(nextCode));
        }

        try {
            departmentService.saveDepartment(department);
            model.addAttribute("successMessage", "Lưu khoa thành công");
            return "redirect:/departments";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi lưu khoa: " + e.getMessage());
            model.addAttribute("pageTitle", department.getDepartmentName() != null ? "Chỉnh sửa khoa" : "Tạo khoa mới");
            return "create-department";
        }
    }

    // Xóa khoa
    @PostMapping("/departments/delete/{departmentName}")
    public String deleteDepartment(@PathVariable String departmentName, Model model) {
        try {
            departmentService.deleteDepartment(departmentName);
            model.addAttribute("successMessage", "Xóa khoa thành công");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi xóa khoa: " + e.getMessage());
        }
        return "redirect:/departments";
    }
}