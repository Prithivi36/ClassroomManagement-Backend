package com.Classroom.Classroom.dto;

import com.Classroom.Classroom.Entity.StudentAbsent;
import com.Classroom.Classroom.Entity.StudentInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentAbsentDto {
    private Long id;
    private LocalDate date;
    private int hour;
}
