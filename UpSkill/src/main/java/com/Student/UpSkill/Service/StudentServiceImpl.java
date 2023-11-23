package com.Student.UpSkill.service;

import com.Student.UpSkill.Service.StudentService;
import com.Student.UpSkill.Entities.StudentEntities;
import com.Student.UpSkill.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;


    @Override
    public List<StudentEntities> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public StudentEntities getStudentById(Long id) {
        Optional<StudentEntities> optionalStudent = studentRepository.findById(id);
        return optionalStudent.orElse(null);
    }

    @Override
    public StudentEntities saveStudent(StudentEntities student) {
        return studentRepository.save(student);

    }
    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);

    }
}

