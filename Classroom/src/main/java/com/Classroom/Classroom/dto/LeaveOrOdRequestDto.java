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
public class LeaveOrOdRequestDto {
    private Long id;
    private String type;
    private Long studentId;
    private String studentName;
    private LocalDate date;
    private String reason;
    private Boolean status;
    private String leaveRequestsId;
    private Boolean denied;

}
