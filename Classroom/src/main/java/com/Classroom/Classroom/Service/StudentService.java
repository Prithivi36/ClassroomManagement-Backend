package com.Classroom.Classroom.Service;

import com.Classroom.Classroom.Entity.StudentInfo;
import com.Classroom.Classroom.Repository.StudentRepository;
import com.Classroom.Classroom.dto.StudentDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StudentService {
    StudentRepository studentRepository;

    public StudentDto getStudentInfo(Long id){
        StudentInfo studentInfo=studentRepository.findById(id).get();
        StudentDto studentDto=new StudentDto(
                studentInfo.getId(),
                studentInfo.getStudentName(),
                studentInfo.getBranch(),
                studentInfo.getPhone(),
                studentInfo.getMail(),
                studentInfo.getFatherNumber(),
                studentInfo.getMotherNumber(),
                studentInfo.getBloodGroup(),
                studentInfo.getCGPA(),
                studentInfo.getStudentConcern()

        );
        return studentDto;
    }
}
