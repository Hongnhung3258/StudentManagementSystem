package com.university.management.controller;

import com.university.management.entity.Course;
import com.university.management.entity.Department;
import com.university.management.service.CourseService;
import com.university.management.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {
    
    private final CourseService courseService;
    private final DepartmentService departmentService;
    
    @Autowired
    public CourseController(CourseService courseService, DepartmentService departmentService) {
        this.courseService = courseService;
        this.departmentService = departmentService;
    }
    
    @GetMapping
    public String listCourses(Model model) {
        List<Course> courses = courseService.findAllCourses();
        model.addAttribute("courses", courses);
        return "course/list";
    }
    
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("course", new Course());
        List<Department> departments = departmentService.findAllDepartments();
        model.addAttribute("departments", departments);
        return "course/add";
    }
    
    @PostMapping("/add")
    public String addCourse(
            @ModelAttribute Course course,
            BindingResult result,
            Model model) {
        
        if (courseService.isCourseCodeExists(course.getCourseCode())) {
            result.rejectValue("courseCode", "error.course", "Course code already exists");
        }
        
        if (courseService.isCourseNameExists(course.getCourseName())) {
            result.rejectValue("courseName", "error.course", "Course name already exists");
        }
        
        if (result.hasErrors()) {
            List<Department> departments = departmentService.findAllDepartments();
            model.addAttribute("departments", departments);
            return "course/add";
        }
        
        courseService.saveCourse(course);
        return "redirect:/courses";
    }
    
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Course course = courseService.findById(id);
        if (course == null) {
            return "redirect:/courses";
        }
        
        model.addAttribute("course", course);
        List<Department> departments = departmentService.findAllDepartments();
        model.addAttribute("departments", departments);
        return "course/add";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Integer id) {
        courseService.deleteCourse(id);
        return "redirect:/courses";
    }
}
