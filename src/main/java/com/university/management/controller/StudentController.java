package com.university.management.controller;

import com.university.management.entity.Department;
import com.university.management.entity.Student;
import com.university.management.service.DepartmentService;
import com.university.management.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    
    private final StudentService studentService;
    private final DepartmentService departmentService;
    
    @GetMapping
    public String listStudents(Model model) {
        List<Student> students = studentService.findAllStudents();
        model.addAttribute("students", students);
        return "student/list";
    }
    
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        List<Department> departments = departmentService.findAllDepartments();
        model.addAttribute("departments", departments);
        return "student/add";
    }
    
    @PostMapping("/add")
    public String addStudent(
            @ModelAttribute Student student,
            BindingResult result,
            Model model) {
        
        if (student.getIdNumber() != null && studentService.isIdNumberExists(student.getIdNumber())) {
            result.rejectValue("idNumber", "error.student", "ID number already exists");
        }
        
        if (result.hasErrors()) {
            List<Department> departments = departmentService.findAllDepartments();
            model.addAttribute("departments", departments);
            return "student/add";
        }
        
        studentService.saveStudent(student);
        return "redirect:/students";
    }
    
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Student student = studentService.findById(id);
        if (student == null) {
            return "redirect:/students";
        }
        
        model.addAttribute("student", student);
        List<Department> departments = departmentService.findAllDepartments();
        model.addAttribute("departments", departments);
        return "student/add";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }
}
