package com.example.controller;

import com.example.entity.Lecturer;
import com.example.service.DepartmentService;
import com.example.service.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class LecturerController {

    @Autowired
    private LecturerService lecturerService;

    @Autowired
    private DepartmentService departmentService;

    // Hiển thị danh sách giảng viên với tìm kiếm và phân trang
    @GetMapping("/lecturers")
    public String listLecturers(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Lecturer> lecturers = lecturerService.searchLecturers(keyword, pageable);
        model.addAttribute("lecturers", lecturers);
        model.addAttribute("keyword", keyword);
        return "lecturers";
    }

    // Hiển thị biểu mẫu tạo giảng viên mới
    @GetMapping("/lecturers/add")
    public String showAddLecturerForm(Model model) {
        model.addAttribute("lecturer", new Lecturer());
        model.addAttribute("departments", departmentService.findAll());
        model.addAttribute("pageTitle", "Tạo giảng viên mới");
        return "create-lecturer";
    }

    // Hiển thị biểu mẫu chỉnh sửa giảng viên
    @GetMapping("/lecturers/edit/{id}")
    public String showEditLecturerForm(@PathVariable Integer id, Model model) {
        Lecturer lecturer = lecturerService.findById(id);
        if (lecturer == null) {
            model.addAttribute("errorMessage", "Giảng viên không tồn tại");
            return "redirect:/lecturers";
        }
        model.addAttribute("lecturer", lecturer);
        model.addAttribute("departments", departmentService.findAll());
        model.addAttribute("pageTitle", "Chỉnh sửa giảng viên");
        return "create-lecturer";
    }

    // Xử lý tạo hoặc cập nhật giảng viên
    @PostMapping("/lecturers/add")
    public String saveLecturer(@Valid @ModelAttribute("lecturer") Lecturer lecturer,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("departments", departmentService.findAll());
            model.addAttribute("pageTitle", lecturer.getId() != null ? "Chỉnh sửa giảng viên" : "Tạo giảng viên mới");
            return "create-lecturer";
        }

        try {
            lecturerService.saveLecturer(lecturer);
            model.addAttribute("successMessage", "Lưu giảng viên thành công");
            return "redirect:/lecturers";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi lưu giảng viên: " + e.getMessage());
            model.addAttribute("departments", departmentService.findAll());
            model.addAttribute("pageTitle", lecturer.getId() != null ? "Chỉnh sửa giảng viên" : "Tạo giảng viên mới");
            return "create-lecturer";
        }
    }

    // Xử lý xóa giảng viên
    @PostMapping("/lecturers/delete/{id}")
    public String deleteLecturer(@PathVariable Integer id, Model model) {
        try {
            lecturerService.deleteLecturer(id);
            model.addAttribute("successMessage", "Xóa giảng viên thành công");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi xóa giảng viên: " + e.getMessage());
        }
        return "redirect:/lecturers";
    }
}