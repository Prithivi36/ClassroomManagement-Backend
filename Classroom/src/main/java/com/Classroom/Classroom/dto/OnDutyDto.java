package com.Classroom.Classroom.dto;

import com.Classroom.Classroom.Entity.StudentInfo;
import jakarta.persistence.ManyToMany;
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
public class OnDutyDto {
    private Long id;
    private LocalDate date;

    private List<StudentInfo> onDutyMembers;
}
