package com.Student.UpSkill.Service;

import com.Student.UpSkill.Entities.StudentEntities;

import java.util.List;

public interface StudentService {
    List<StudentEntities> getAllStudents();
    StudentEntities getStudentById(Long id);
    StudentEntities saveStudent(StudentEntities student);
    void deleteStudent(Long id);
}
