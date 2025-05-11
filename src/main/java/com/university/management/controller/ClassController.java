package com.example.controller;

import com.example.entity.Class;
import com.example.entity.Course;
import com.example.entity.Department;
import com.example.entity.Lecturer;
import com.example.service.ClassService;
import com.example.service.CourseService;
import com.example.service.DepartmentService;
import com.example.service.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.text.DecimalFormat;

@Controller
public class ClassController {

    @Autowired
    private ClassService classService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private LecturerService lecturerService;

    // Hiển thị danh sách lớp học với phân trang
    @GetMapping("/classes")
    public String listClasses(Model model,
                              @RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "10") int size) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "classCode"));
        model.addAttribute("classes", classService.findAllPaged(pageable));
        return "classes";
    }

    // Hiển thị biểu mẫu tạo lớp học mới
    @GetMapping("/classes/add")
    public String showAddClassForm(Model model) {
        model.addAttribute("classEntity", new Class());
        model.addAttribute("departments", departmentService.findAll());
        model.addAttribute("courses", courseService.findAll());
        model.addAttribute("lecturers", lecturerService.findAll());
        model.addAttribute("pageTitle", "Thêm Lớp học");
        return "create-class";
    }

    // Hiển thị biểu mẫu chỉnh sửa lớp học
    @GetMapping("/classes/edit/{id}")
    public String showEditClassForm(@PathVariable Integer id, Model model) {
        Class classEntity = classService.findById(id);
        if (classEntity == null) {
            model.addAttribute("errorMessage", "Lớp học không tồn tại");
            return "redirect:/classes";
        }
        model.addAttribute("classEntity", classEntity);
        model.addAttribute("departments", departmentService.findAll());
        model.addAttribute("courses", courseService.findAll());
        model.addAttribute("lecturers", lecturerService.findAll());
        model.addAttribute("pageTitle", "Chỉnh sửa Lớp học");
        return "create-class";
    }

    // Xử lý tạo hoặc cập nhật lớp học
    @PostMapping("/classes/add")
    public String saveClass(@Valid @ModelAttribute("classEntity") Class classEntity,
                            BindingResult result, Model model) {
        // Kiểm tra lỗi xác thực
        if (result.hasErrors()) {
            model.addAttribute("departments", departmentService.findAll());
            model.addAttribute("courses", courseService.findAll());
            model.addAttribute("lecturers", lecturerService.findAll());
            model.addAttribute("pageTitle", classEntity.getId() != null ? "Chỉnh sửa Lớp học" : "Thêm Lớp học");
            return "create-class";
        }

        // Tự động tạo mã lớp nếu trống
        if (classEntity.getClassCode() == null || classEntity.getClassCode().trim().isEmpty()) {
            String maxCode = classService.findMaxClassCode();
            int nextCode = 1;
            if (maxCode != null && maxCode.matches("\\d+")) {
                nextCode = Integer.parseInt(maxCode) + 1;
            }
            classEntity.setClassCode(new DecimalFormat("00000").format(nextCode));
        }

        try {
            classService.saveClass(classEntity);
            model.addAttribute("successMessage", "Lưu lớp học thành công");
            return "redirect:/classes";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi lưu lớp học: " + e.getMessage());
            model.addAttribute("departments", departmentService.findAll());
            model.addAttribute("courses", courseService.findAll());
            model.addAttribute("lecturers", lecturerService.findAll());
            model.addAttribute("pageTitle", classEntity.getId() != null ? "Chỉnh sửa Lớp học" : "Thêm Lớp học");
            return "create-class";
        }
    }

    // Xử lý xóa lớp học
    @PostMapping("/classes/delete/{id}")
    public String deleteClass(@PathVariable Integer id, Model model) {
        try {
            Class classEntity = classService.findById(id);
            if (classEntity == null) {
                model.addAttribute("errorMessage", "Lớp học không tồn tại");
            } else {
                classService.deleteClass(id);
                model.addAttribute("successMessage", "Xóa lớp học thành công");
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi xóa lớp học: " + e.getMessage());
        }
        return "redirect:/classes";
    }
}