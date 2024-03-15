package com.Classroom.Classroom.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LeaveRequestDto {
    private Long id;
    private int studentId;
    private int StudentName;
    private LocalDate date;
    private String reason;
    private Boolean status;
}
