package com.Student.UpSkill.Service;

import com.Student.UpSkill.Entities.StudentEntities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class StudentServiceTest {

    @Mock
    private StudentService studentService;

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


        when(studentService.getAllStudents()).thenReturn(mockStudents);


        List<StudentEntities> result = studentService.getAllStudents();


        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getName());
        assertEquals("Jane Doe", result.get(1).getName());


        verify(studentService, times(1)).getAllStudents();
    }

    @Test
    public void testGetStudentById() {

        Long studentId = 1L;
        StudentEntities mockStudent = new StudentEntities(22, "John Doe", 55000.0);
        mockStudent.setId(studentId);


        when(studentService.getStudentById(studentId)).thenReturn(mockStudent);


        StudentEntities result = studentService.getStudentById(studentId);


        assertNotNull(result);
        assertEquals("John Doe", result.getName());


        verify(studentService, times(1)).getStudentById(studentId);
    }


}

