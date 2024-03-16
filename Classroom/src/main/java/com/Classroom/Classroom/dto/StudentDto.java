package com.Classroom.Classroom.dto;

import com.Classroom.Classroom.Entity.LeaveRequestEntity;
import com.Classroom.Classroom.Entity.OnDutyEntity;
import com.Classroom.Classroom.Entity.StudentAbsent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    private Long id;
    private int regNo;
    private String studentName;
    private String branch;
    private int phone;
    private String mail;
    private String fatherNumber;
    private String motherNumber;
    private String bloodGroup;
    private int CGPA;
    private int studentConcern;
    private String password;
    private List<OnDutyEntity> onDutyEntities;
    private List<StudentAbsent> absentList;
    private List<LeaveRequestEntity> leaveRequests;

}
