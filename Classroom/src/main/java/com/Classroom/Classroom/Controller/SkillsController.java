package com.Classroom.Classroom.Controller;

import com.Classroom.Classroom.Service.SkillService;
import com.Classroom.Classroom.dto.StudentDto;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/skills")
public class SkillsController {
    private SkillService skillService;
    @PreAuthorize("permitAll()")
    @GetMapping("/{skill}")
    public List<StudentDto> getStudentInfo(@PathVariable String skill){
        return skillService.findBySkill(skill);
    }
}
