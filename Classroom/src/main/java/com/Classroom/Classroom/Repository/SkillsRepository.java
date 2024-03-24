package com.Classroom.Classroom.Repository;

import com.Classroom.Classroom.Entity.StudentSkills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SkillsRepository extends JpaRepository<StudentSkills,Long> {
    Optional<StudentSkills> findBySkill(String skill);
}
