package com.Classroom.Classroom.ServiceImpl;

import com.Classroom.Classroom.Entity.StudentInfo;
import com.Classroom.Classroom.Repository.StudentRepository;
import com.Classroom.Classroom.Service.StudentInfoService;
import com.Classroom.Classroom.dto.LeaveRequestDto;
import com.Classroom.Classroom.dto.OnDutyDto;
import com.Classroom.Classroom.dto.StudentAbsentDto;
import com.Classroom.Classroom.dto.StudentDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentInfoServiceImpl implements StudentInfoService {

    private StudentRepository studentRepository;
    private ModelMapper modelMapper;

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
    public StudentDto getSpecificStudent(int regNo) {
        StudentInfo foundStudent= studentRepository.findByRegNo(regNo).get();
        return modelMapper.map(foundStudent,StudentDto.class);
    }

    @Override
    public StudentAbsentDto getStudentAbsent(int regNo) {
        StudentInfo foundStudent=studentRepository.findByRegNo(regNo).get();
        return modelMapper.map(foundStudent.getAbsentList(), StudentAbsentDto.class);
    }

    @Override
    public OnDutyDto getStudentOnDuty(int regNo) {
        StudentInfo foundStudent=studentRepository.findByRegNo(regNo).get();
        return modelMapper.map(foundStudent.getOnDutyEntities(), OnDutyDto.class);
    }

    @Override
    public LeaveRequestDto getStudentLeaveRequests(int regNo) {
        StudentInfo foundStudent=studentRepository.findByRegNo(regNo).get();
        return modelMapper.map(foundStudent.getLeaveRequests(), LeaveRequestDto.class);
    }
}
