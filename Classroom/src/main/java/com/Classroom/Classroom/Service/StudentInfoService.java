package com.Classroom.Classroom.Service;

import com.Classroom.Classroom.Repository.StudentRepository;
import com.Classroom.Classroom.dto.StudentDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentInfoService {

    String registerNewStudent(StudentDto studentData);

    List<StudentDto> getAllStudentsInfo();

    StudentDto getSpecificStudent();

    StudentDto getStudentAbsent();

    StudentDto getStudentOnDuty();

    StudentDto getStudentLeaveRequests();
}
