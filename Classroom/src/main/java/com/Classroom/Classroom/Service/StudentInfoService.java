package com.Classroom.Classroom.Service;

import com.Classroom.Classroom.Repository.StudentRepository;
import com.Classroom.Classroom.dto.LeaveRequestDto;
import com.Classroom.Classroom.dto.OnDutyDto;
import com.Classroom.Classroom.dto.StudentAbsentDto;
import com.Classroom.Classroom.dto.StudentDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentInfoService {

    String registerNewStudent(StudentDto studentData);

    List<StudentDto> getAllStudentsInfo();

    StudentDto getSpecificStudent(int regNo);

    StudentAbsentDto getStudentAbsent(int regNo);

    OnDutyDto getStudentOnDuty(int regNo);

    LeaveRequestDto getStudentLeaveRequests(int regNo);
}
