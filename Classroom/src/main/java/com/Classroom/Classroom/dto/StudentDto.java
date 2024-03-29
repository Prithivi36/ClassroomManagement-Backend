package com.Classroom.Classroom.dto;

import com.Classroom.Classroom.Entity.LeaveOrOdRequestEntity;
import com.Classroom.Classroom.Entity.OnDutyEntity;
import com.Classroom.Classroom.Entity.StudentAbsent;
import com.Classroom.Classroom.Entity.StudentSkills;
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
    private Long regNo;
    private String studentName;
    private String branch;
    private Long phone;
    private String mail;
    private String linkedin;
    private String instagram;
    private Long fatherNumber;
    private Long motherNumber;
    private String bloodGroup;
    private double cgpa;
    private int studentConcern;
    private String password;
    private List<StudentSkills> studentSkills;
    private List<LeaveOrOdRequestEntity> leaveOrOdRequests;

}
