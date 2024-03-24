package com.Classroom.Classroom.Service;

import com.Classroom.Classroom.dto.StudentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SkillService {
    List<StudentDto> findBySkill(String Skill);
}
