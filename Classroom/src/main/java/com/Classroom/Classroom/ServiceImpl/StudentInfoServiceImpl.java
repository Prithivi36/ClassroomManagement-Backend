package com.Classroom.Classroom.ServiceImpl;

import com.Classroom.Classroom.Entity.StudentInfo;
import com.Classroom.Classroom.Entity.StudentSkills;
import com.Classroom.Classroom.Exception.APIException;
import com.Classroom.Classroom.Repository.SkillsRepository;
import com.Classroom.Classroom.Repository.StudentRepository;
import com.Classroom.Classroom.Service.StudentInfoService;
import com.Classroom.Classroom.dto.LeaveOrOdRequestDto;
import com.Classroom.Classroom.dto.OnDutyDto;
import com.Classroom.Classroom.dto.StudentAbsentDto;
import com.Classroom.Classroom.dto.StudentDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentInfoServiceImpl implements StudentInfoService {

    private StudentRepository studentRepository;
    private ModelMapper modelMapper;
    private SkillsRepository skillsRepository;

    @Override
    public String registerNewStudent(StudentDto studentData) {
        studentRepository.save(modelMapper.map(studentData, StudentInfo.class));
        return "Successfully Inserted";
    }

    @Override
    public List<StudentDto> getAllStudentsInfo() {
        List<StudentInfo> allStudents=studentRepository.findAll();
        return allStudents.stream().map((student->modelMapper.map(student, StudentDto.class))).toList();
    }

    @Override
    public StudentDto getSpecificStudent(Long regNo) {
        StudentInfo foundStudent= studentRepository.findByRegNo(regNo).orElseThrow(()->new APIException(HttpStatus.NOT_FOUND,"User Not Found"));
        return modelMapper.map(foundStudent,StudentDto.class);
    }

    @Override
    public List<StudentAbsentDto> getStudentAbsent(Long regNo) {
        StudentInfo foundStudent=studentRepository.findByRegNo(regNo).get();
        return foundStudent.getAbsentList().stream().map((abs)->modelMapper.map(abs,StudentAbsentDto.class)).toList();
    }

    @Override
    public List<OnDutyDto> getStudentOnDuty(Long regNo) {
        StudentInfo foundStudent=studentRepository.findByRegNo(regNo).get();
        return foundStudent.getOnDutyEntities().stream().map((ond)->modelMapper.map(ond, OnDutyDto.class)).toList();
    }

    @Override
    public List<LeaveOrOdRequestDto> getStudentLeaveRequests(Long regNo) {
        StudentInfo foundStudent=studentRepository.findByRegNo(regNo).get();
        return foundStudent.getLeaveOrOdRequests().stream().map((req)->modelMapper.map(req, LeaveOrOdRequestDto.class)).toList();
    }

    @Override
    public String increaseCount(Long regNo) {
        StudentInfo foundStudent= studentRepository.findByRegNo(regNo).get();
        foundStudent.setStudentConcern(foundStudent.getStudentConcern()+1);
        studentRepository.save(foundStudent);
        return "Success";
    }

    @Override
    public String decreaseCount(Long regNo) {
        StudentInfo foundStudent= studentRepository.findByRegNo(regNo).get();
        foundStudent.setStudentConcern(foundStudent.getStudentConcern()-1);
        studentRepository.save(foundStudent);
        return "Success";
    }

    @Override
    public String addSkills(String skill,Long student) {
        StudentSkills skillsFound=skillsRepository.findBySkill(skill).orElseGet(()->{
            StudentSkills skillsCreated=new StudentSkills();
            skillsCreated.setSkill(skill);
            return skillsCreated;
        });
        skillsRepository.save(skillsFound);

        StudentInfo foundStudent=studentRepository.findByRegNo(student).get();
        foundStudent.getStudentSkills().add(skillsFound);
        studentRepository.save(foundStudent);
        return "Success";
    }
}
