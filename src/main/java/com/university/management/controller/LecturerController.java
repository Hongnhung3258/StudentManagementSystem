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
@RequestMapping("/lecturers")
@RequiredArgsConstructor
public class LecturerController {
    
    private final LecturerService lecturerService;
    private final DepartmentService departmentService;
    
    @GetMapping
    public String listLecturers(Model model) {
        List<Lecturer> lecturers = lecturerService.findAllLecturers();
        model.addAttribute("lecturers", lecturers);
        return "lecturer/list";
    }
    
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("lecturer", new Lecturer());
        List<Department> departments = departmentService.findAllDepartments();
        model.addAttribute("departments", departments);
        return "lecturer/add";
    }
    
    @PostMapping("/add")
    public String addLecturer(
            @ModelAttribute Lecturer lecturer,
            BindingResult result,
            Model model) {
        
        if (lecturer.getIdNumber() != null && lecturerService.isIdNumberExists(lecturer.getIdNumber())) {
            result.rejectValue("idNumber", "error.lecturer", "ID number already exists");
        }
        
        if (result.hasErrors()) {
            List<Department> departments = departmentService.findAllDepartments();
            model.addAttribute("departments", departments);
            return "lecturer/add";
        }
        
        lecturerService.saveLecturer(lecturer);
        return "redirect:/lecturers";
    }
    
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Lecturer lecturer = lecturerService.findById(id);
        if (lecturer == null) {
            return "redirect:/lecturers";
        }
        
        model.addAttribute("lecturer", lecturer);
        List<Department> departments = departmentService.findAllDepartments();
        model.addAttribute("departments", departments);
        return "lecturer/add";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteLecturer(@PathVariable Integer id) {
        lecturerService.deleteLecturer(id);
        return "redirect:/lecturers";
    }
}
