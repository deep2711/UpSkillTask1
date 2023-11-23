package com.Student.UpSkill.Controller;

import com.Student.UpSkill.Entities.StudentEntities;
import com.Student.UpSkill.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
    @Autowired
    private StudentService studentService;

    @GetMapping("/all")
    public ResponseEntity<List<StudentEntities>> getAllStudents() {
        logger.info("Fetching all students");
        List<StudentEntities> students = studentService.getAllStudents();
        logger.debug("Number of students fetched: {}", students.size());
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentEntities> getStudentById(@PathVariable Long id) {
        logger.info("Fetching student with id: {}", id);
        StudentEntities student = studentService.getStudentById(id);
        if (student != null) {
            logger.info("Found student: {}", student);
            return new ResponseEntity<>(student, HttpStatus.OK);
        } else {
            logger.warn("Student with id {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/add")
    public ResponseEntity<StudentEntities> addStudent(@RequestBody StudentEntities student) {
        logger.info("Adding new student: {}", student);
        StudentEntities createdStudent = studentService.saveStudent(student);
        logger.info("Student added successfully: {}", createdStudent);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        logger.info("Deleting student with id: {}", id);
        studentService.deleteStudent(id);
        logger.info("Student deleted successfully");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
