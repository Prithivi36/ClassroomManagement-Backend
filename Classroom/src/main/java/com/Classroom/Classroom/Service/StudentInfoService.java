package com.Classroom.Classroom.Service;

import com.Classroom.Classroom.dto.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentInfoService {

    String registerNewStudent(StudentDto studentData);

    List<StudentDto> getAllStudentsInfo();

    StudentDto getSpecificStudent(Long regNo);

    List<StudentAbsentDto> getStudentAbsent(Long regNo);

    List<OnDutyDto> getStudentOnDuty(Long regNo);

    List<LeaveOrOdRequestDto> getStudentLeaveRequests(Long regNo);

    String increaseCount(Long regNo);
    String decreaseCount(Long regNo);

    String addSkills(String skill ,Long student);
}
