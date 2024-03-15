package com.Classroom.Classroom.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    private Long id;
    private String studentName;
    private String branch;
    private int phone;
    private String mail;
    private String fatherNumber;
    private String motherNumber;
    private String bloodGroup;
    private int CGPA;
    private int studentConcern;

}
