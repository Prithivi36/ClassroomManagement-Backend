package com.Classroom.Classroom.ServiceImpl;

import com.Classroom.Classroom.Entity.StudentInfo;
import com.Classroom.Classroom.Entity.StudentSkills;
import com.Classroom.Classroom.Repository.SkillsRepository;
import com.Classroom.Classroom.Service.SkillService;
import com.Classroom.Classroom.dto.StudentDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SkillServiceImpl implements SkillService {

    private SkillsRepository skillsRepository;
    private ModelMapper modelMapper;

    @Override
    public List<StudentDto> findBySkill(String skill) {
        StudentSkills skillsFound=skillsRepository.findBySkill(skill).get();
        List<StudentInfo> foundStudent=skillsFound.getStudentInfos();
        return foundStudent.stream().map((stud)->modelMapper.map(stud,StudentDto.class)).toList();
    }
}
