package com.Student.UpSkill.Entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class StudentEntitiesTest {

    @Test
    public void testCreateStudent() {
        StudentEntities student = new StudentEntities();
        assertNotNull(student);
    }

    @Test
    public void testGetAndSetMethods() {
        StudentEntities student = new StudentEntities();


        student.setId(1L);
        student.setAge(20);
        student.setName("Divyanshu");
        student.setSalary(50000.0);


        assertEquals(1L, student.getId());
        assertEquals(20, student.getAge());
        assertEquals("Divyanshu", student.getName());
        assertEquals(50000.0, student.getSalary(), 0.001);
    }

    @Test
    public void testToStringMethod() {
        StudentEntities student = new StudentEntities(25, "Divyanshu", 60000.0);
        String expectedToString = "StudentEntities [id=null, age=25, name=Divyanshu, salary=60000.0]";
        assertEquals(expectedToString, student.toString());
    }
}

