package com.Student.UpSkill.Service;

import com.Student.UpSkill.entities.StudentEntities;

import java.util.List;

public interface StudentService {
    List<StudentEntities> getAllStudents();
    StudentEntities getStudentById(Long id);
    void saveStudent(StudentEntities student);
    void deleteStudent(Long id);
}
