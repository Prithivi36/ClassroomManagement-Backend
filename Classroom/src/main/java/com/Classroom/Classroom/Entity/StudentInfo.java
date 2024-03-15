package com.Classroom.Classroom.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "studentDetail")
public class StudentInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
