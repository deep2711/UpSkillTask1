package com.Student.UpSkill.repository;

import com.Student.UpSkill.entities.StudentEntities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntities,Long> {
}
