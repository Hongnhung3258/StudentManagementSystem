package com.university.management.controller;

import com.university.management.entity.Class;
import com.university.management.entity.Course;
import com.university.management.entity.Department;
import com.university.management.entity.Lecturer;
import com.university.management.service.ClassService;
import com.university.management.service.CourseService;
import com.university.management.service.DepartmentService;
import com.university.management.service.LecturerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/classes")
@RequiredArgsConstructor
public class ClassController {
    
    private final ClassService classService;
    private final CourseService courseService;
    private final DepartmentService departmentService;
    private final LecturerService lecturerService;
    
    @GetMapping
    public String listClasses(Model model) {
        List<Class> classes = classService.findAllClasses();
        model.addAttribute("classes", classes);
        return "class/list";
    }
    
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("classEntity", new Class());
        
        List<Course> courses = courseService.findAllCourses();
        List<Department> departments = departmentService.findAllDepartments();
        List<Lecturer> lecturers = lecturerService.findAllLecturers();
        
        model.addAttribute("courses", courses);
        model.addAttribute("departments", departments);
        model.addAttribute("lecturers", lecturers);
        
        return "class/add";
    }
    
    @PostMapping("/add")
    public String addClass(
            @ModelAttribute("classEntity") Class classEntity,
            BindingResult result,
            Model model) {
        
        if (classService.isClassCodeExists(classEntity.getClassCode())) {
            result.rejectValue("classCode", "error.class", "Class code already exists");
        }
        
        if (result.hasErrors()) {
            List<Course> courses = courseService.findAllCourses();
            List<Department> departments = departmentService.findAllDepartments();
            List<Lecturer> lecturers = lecturerService.findAllLecturers();
            
            model.addAttribute("courses", courses);
            model.addAttribute("departments", departments);
            model.addAttribute("lecturers", lecturers);
            
            return "class/add";
        }
        
        classService.saveClass(classEntity);
        return "redirect:/classes";
    }
    
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Class classEntity = classService.findById(id);
        if (classEntity == null) {
            return "redirect:/classes";
        }
        
        model.addAttribute("classEntity", classEntity);
        
        List<Course> courses = courseService.findAllCourses();
        List<Department> departments = departmentService.findAllDepartments();
        List<Lecturer> lecturers = lecturerService.findAllLecturers();
        
        model.addAttribute("courses", courses);
        model.addAttribute("departments", departments);
        model.addAttribute("lecturers", lecturers);
        
        return "class/add";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteClass(@PathVariable Integer id) {
        classService.deleteClass(id);
        return "redirect:/classes";
    }
}
