package com.Student.UpSkill.Service;

import com.Student.UpSkill.Entities.StudentEntities;
import com.Student.UpSkill.Repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private com.Student.UpSkill.service.StudentServiceImpl studentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllStudents() {

        List<StudentEntities> mockStudents = Arrays.asList(
                new StudentEntities(22, "John Doe", 55000.0),
                new StudentEntities(25, "Jane Doe", 60000.0)
        );


        when(studentRepository.findAll()).thenReturn(mockStudents);


        List<StudentEntities> result = studentService.getAllStudents();


        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getName());
        assertEquals("Jane Doe", result.get(1).getName());


        verify(studentRepository, times(1)).findAll();
    }

    @Test
    public void testGetStudentById() {

        Long studentId = 1L;
        StudentEntities mockStudent = new StudentEntities(22, "John Doe", 55000.0);
        mockStudent.setId(studentId);


        when(studentRepository.findById(studentId)).thenReturn(Optional.of(mockStudent));


        StudentEntities result = studentService.getStudentById(studentId);


        assertNotNull(result);
        assertEquals("John Doe", result.getName());


        verify(studentRepository, times(1)).findById(studentId);
    }


}

