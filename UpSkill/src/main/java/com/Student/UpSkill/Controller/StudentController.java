package com.Student.UpSkill.Controller;

import com.Student.UpSkill.entities.StudentEntities;
import com.Student.UpSkill.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/all")
    public String getAllStudents(Model model) {
        List<StudentEntities> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "studentList";
    }

    @GetMapping("/{id}")
    public String getStudentById(@PathVariable Long id, Model model) {
        StudentEntities student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        return "studentDetails";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("student", new StudentEntities());
        return "addStudent";
    }

    @PostMapping("/add")
    public String addStudent(@ModelAttribute("student") StudentEntities student) {
        studentService.saveStudent(student);
        return "redirect:/students/all";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        StudentEntities student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        return "editStudent";
    }

    @PostMapping("/edit/{id}")
    public String editStudent(@PathVariable Long id, @ModelAttribute("student") StudentEntities student) {
        student.setId(id);
        studentService.saveStudent(student);
        return "redirect:/students/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/students/all";
    }
}
//Done
