package com.Student.UpSkill.Repository;

import com.Student.UpSkill.Entities.StudentEntities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntities,Long> {
}
