package com.Student.UpSkill.Repository;

import com.Student.UpSkill.Entities.StudentEntities;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void testSaveAndFindById() {

        StudentEntities student = new StudentEntities(22, "John Doe", 55000.0);

        StudentEntities savedStudent = studentRepository.save(student);

        Optional<StudentEntities> foundStudent = studentRepository.findById(savedStudent.getId());

        assertTrue(foundStudent.isPresent());
        assertEquals(student.getAge(), foundStudent.get().getAge());
        assertEquals(student.getName(), foundStudent.get().getName());
        assertEquals(student.getSalary(), foundStudent.get().getSalary(), 0.001); // Using delta for double comparison
    }

    @Test
    public void testFindByNonExistentId() {

        Optional<StudentEntities> foundStudent = studentRepository.findById(999L);

        assertFalse(foundStudent.isPresent());
    }

    @Test
    public void testFindAll() {

        StudentEntities student1 = new StudentEntities(22, "John Doe", 55000.0);
        StudentEntities student2 = new StudentEntities(25, "Jane Doe", 60000.0);
        entityManager.persist(student1);
        entityManager.persist(student2);


        List<StudentEntities> allStudents = studentRepository.findAll();

        assertEquals(2, allStudents.size());
    }
}

