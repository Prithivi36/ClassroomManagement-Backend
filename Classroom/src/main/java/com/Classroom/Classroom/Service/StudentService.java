package com.Classroom.Classroom.Service;

import com.Classroom.Classroom.Entity.StudentInfo;
import com.Classroom.Classroom.Repository.StudentRepository;
import com.Classroom.Classroom.dto.StudentDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StudentService {
    private StudentRepository studentRepository;
    private ModelMapper modelMapper;

    public StudentDto getStudentInfo(Long id){
        StudentInfo studentInfo=studentRepository.findById(id).get();
        StudentDto studentDto=modelMapper.map(studentInfo, StudentDto.class);
        return studentDto;
    }
}
