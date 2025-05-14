package com.university.management.controller;

import com.university.management.entity.Lecturer;
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

import java.util.regex.Pattern;

@Controller
public class LecturerController {

    @Autowired
    private LecturerService lecturerService;

    @Autowired
    private DepartmentService departmentService;

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
        model.addAttribute("pageTitle", "Quản lý giảng viên");
        if (lecturers.isEmpty() && keyword != null && !keyword.isEmpty()) {
            model.addAttribute("errorMessage", "Không tìm thấy giảng viên phù hợp với từ khóa: " + keyword);
        }
        return "admin/lecturer/management";
    }

    @GetMapping("/lecturers/add")
    public String showAddLecturerForm(Model model) {
        model.addAttribute("lecturer", new Lecturer());
        model.addAttribute("departments", departmentService.findAll());
        model.addAttribute("pageTitle", "Tạo giảng viên mới");
        return "admin/lecturer/add";
    }

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
        return "admin/lecturer/add";
    }

    @PostMapping("/lecturers/add")
    public String saveLecturer(@Valid @ModelAttribute("lecturer") Lecturer lecturer,
                               BindingResult result, Model model) {
        // Check validation errors from annotations
        if (result.hasErrors()) {
            model.addAttribute("departments", departmentService.findAll());
            model.addAttribute("pageTitle", lecturer.getId() != null ? "Chỉnh sửa giảng viên" : "Tạo giảng viên mới");
            return "admin/lecturer/add";
        }

        try {
            // Validate lecturerId
            String lecturerId = lecturer.getLecturerId();
            if (lecturerId == null || lecturerId.isEmpty()) {
                lecturer.setLecturerId(lecturerService.generateLecturerCode());
            } else {
                // Validate format (GV + 4 digits, max 6 chars)
                if (!Pattern.matches("^GV\\d{4}$", lecturerId)) {
                    result.rejectValue("lecturerId", "error.lecturer", "Mã giảng viên phải có định dạng GVxxxx (xxxx là 4 số)");
                } else if (lecturer.getId() == null && lecturerService.existsByLecturerId(lecturerId)) {
                    result.rejectValue("lecturerId", "error.lecturer", "Mã giảng viên đã tồn tại");
                } else if (lecturer.getId() != null) {
                    Lecturer existing = lecturerService.findById(lecturer.getId());
                    if (!existing.getLecturerId().equals(lecturerId) &&
                            lecturerService.existsByLecturerId(lecturerId)) {
                        result.rejectValue("lecturerId", "error.lecturer", "Mã giảng viên đã tồn tại");
                    }
                }
            }

            // Validate nationalId uniqueness
            if (lecturer.getNationalId() != null && !lecturer.getNationalId().isEmpty()) {
                if (!Pattern.matches("^\\d{12}$", lecturer.getNationalId())) {
                    result.rejectValue("nationalId", "error.lecturer", "CMND/CCCD phải có 12 chữ số");
                } else if (lecturer.getId() == null && lecturerService.existsByNationalId(lecturer.getNationalId())) {
                    result.rejectValue("nationalId", "error.lecturer", "CMND/CCCD đã tồn tại");
                } else if (lecturer.getId() != null) {
                    Lecturer existing = lecturerService.findById(lecturer.getId());
                    if (!existing.getNationalId().equals(lecturer.getNationalId()) &&
                            lecturerService.existsByNationalId(lecturer.getNationalId())) {
                        result.rejectValue("nationalId", "error.lecturer", "CMND/CCCD đã tồn tại");
                    }
                }
            }

            // Validate phoneNumber uniqueness
            if (lecturer.getPhoneNumber() != null && !lecturer.getPhoneNumber().isEmpty()) {
                if (!Pattern.matches("^\\d{10,15}$", lecturer.getPhoneNumber())) {
                    result.rejectValue("phoneNumber", "error.lecturer", "Số điện thoại phải có 10-15 chữ số");
                } else if (lecturer.getId() == null && lecturerService.existsByPhoneNumber(lecturer.getPhoneNumber())) {
                    result.rejectValue("phoneNumber", "error.lecturer", "Số điện thoại đã tồn tại");
                } else if (lecturer.getId() != null) {
                    Lecturer existing = lecturerService.findById(lecturer.getId());
                    if (!existing.getPhoneNumber().equals(lecturer.getPhoneNumber()) &&
                            lecturerService.existsByPhoneNumber(lecturer.getPhoneNumber())) {
                        result.rejectValue("phoneNumber", "error.lecturer", "Số điện thoại đã tồn tại");
                    }
                }
            }

            // Validate department
            if (lecturer.getDepartmentName() != null && !lecturer.getDepartmentName().isEmpty()) {
                if (!departmentService.existsByDepartmentName(lecturer.getDepartmentName())) {
                    result.rejectValue("departmentName", "error.lecturer", "Khoa không tồn tại");
                }
            }

            if (result.hasErrors()) {
                model.addAttribute("departments", departmentService.findAll());
                model.addAttribute("pageTitle", lecturer.getId() != null ? "Chỉnh sửa giảng viên" : "Tạo giảng viên mới");
                return "admin/lecturer/add";
            }

            lecturerService.saveLecturer(lecturer);
            model.addAttribute("successMessage", "Lưu giảng viên thành công");
            model.addAttribute("lecturer", new Lecturer()); // Reset form
            model.addAttribute("departments", departmentService.findAll());
            model.addAttribute("pageTitle", "Tạo giảng viên mới");
            return "admin/lecturer/add"; // Stay on add page
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi lưu giảng viên: " + e.getMessage());
            model.addAttribute("departments", departmentService.findAll());
            model.addAttribute("pageTitle", lecturer.getId() != null ? "Chỉnh sửa giảng viên" : "Tạo giảng viên mới");
            return "admin/lecturer/add";
        }
    }

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