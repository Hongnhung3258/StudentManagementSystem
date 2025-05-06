package com.university.management.controller;

import com.university.management.entity.Department;
import com.university.management.entity.Lecturer;
import com.university.management.service.DepartmentService;
import com.university.management.service.LecturerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {
    
    private final DepartmentService departmentService;
    private final LecturerService lecturerService;
    
    @GetMapping
    public String listDepartments(Model model) {
        List<Department> departments = departmentService.findAllDepartments();
        model.addAttribute("departments", departments);
        return "department/list";
    }
    
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("department", new Department());
        List<Lecturer> lecturers = lecturerService.findAllLecturers();
        model.addAttribute("lecturers", lecturers);
        return "department/add";
    }
    
    @PostMapping("/add")
    public String addDepartment(
            @ModelAttribute Department department,
            BindingResult result,
            Model model) {
        
        if (departmentService.isDepartmentCodeExists(department.getDepartmentCode())) {
            result.rejectValue("departmentCode", "error.department", "Department code already exists");
        }
        
        if (departmentService.isDepartmentNameExists(department.getDepartmentName())) {
            result.rejectValue("departmentName", "error.department", "Department name already exists");
        }
        
        if (result.hasErrors()) {
            List<Lecturer> lecturers = lecturerService.findAllLecturers();
            model.addAttribute("lecturers", lecturers);
            return "department/add";
        }
        
        departmentService.saveDepartment(department);
        return "redirect:/departments";
    }
    
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Department department = departmentService.findById(id);
        if (department == null) {
            return "redirect:/departments";
        }
        
        model.addAttribute("department", department);
        List<Lecturer> lecturers = lecturerService.findAllLecturers();
        model.addAttribute("lecturers", lecturers);
        return "department/add";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable Integer id) {
        departmentService.deleteDepartment(id);
        return "redirect:/departments";
    }
}
