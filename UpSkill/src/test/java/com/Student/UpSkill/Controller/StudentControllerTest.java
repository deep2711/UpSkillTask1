package com.Student.UpSkill.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.Student.UpSkill.Entities.StudentEntities;
import com.Student.UpSkill.Service.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    private MockMvc mockMvc;

    @Test
    public void testGetAllStudents() throws Exception {

        List<StudentEntities> mockStudents = Arrays.asList(
                new StudentEntities(22, "Divyanshu", 55000.0),
                new StudentEntities(25, "Divyanshu", 60000.0)
        );

        when(studentService.getAllStudents()).thenReturn(mockStudents);

        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();

        mockMvc.perform(get("/students/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2));

        verify(studentService, times(1)).getAllStudents();
    }

    @Test
    public void testGetStudentById() throws Exception {

        Long studentId = 1L;
        StudentEntities mockStudent = new StudentEntities(22, "Divyanshu", 55000.0);
        mockStudent.setId(studentId);

        when(studentService.getStudentById(studentId)).thenReturn(mockStudent);

        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();

        mockMvc.perform(get("/students/{id}", studentId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(studentId))
                .andExpect(jsonPath("$.name").value("Divyanshu"));

        verify(studentService, times(1)).getStudentById(studentId);
    }

    @Test
    public void testAddStudent() throws Exception {

        StudentEntities newStudent = new StudentEntities(22, "Divyanshu", 55000.0);

        when(studentService.saveStudent(any())).thenReturn(newStudent);

        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();

        mockMvc.perform(post("/students/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(newStudent)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Divyanshu"));

        verify(studentService, times(1)).saveStudent(any());
    }

    @Test
    public void testDeleteStudent() throws Exception {
        Long studentId = 1L;

        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();

        mockMvc.perform(delete("/students/delete/{id}", studentId))
                .andExpect(status().isOk());

        verify(studentService, times(1)).deleteStudent(studentId);
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

