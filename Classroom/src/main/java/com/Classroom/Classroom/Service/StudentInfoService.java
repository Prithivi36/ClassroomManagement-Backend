package com.Classroom.Classroom.Service;

import com.Classroom.Classroom.dto.LeaveOrOdRequestDto;
import com.Classroom.Classroom.dto.OnDutyDto;
import com.Classroom.Classroom.dto.StudentAbsentDto;
import com.Classroom.Classroom.dto.StudentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentInfoService {

    String registerNewStudent(StudentDto studentData);

    List<StudentDto> getAllStudentsInfo();

    StudentDto getSpecificStudent(int regNo);

    List<StudentAbsentDto> getStudentAbsent(int regNo);

    List<OnDutyDto> getStudentOnDuty(int regNo);

    List<LeaveOrOdRequestDto> getStudentLeaveRequests(int regNo);
}
