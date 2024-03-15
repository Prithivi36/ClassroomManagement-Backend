package com.Classroom.Classroom.Service;

import com.Classroom.Classroom.Entity.StudentAbsent;
import com.Classroom.Classroom.dto.StudentAbsentDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface AbsentServiceImpl {
    List<StudentAbsentDto> getAllAbsentDetails();

    StudentAbsentDto getAbsentsOnDate(LocalDate specificDate);
    String markDownAbsent(List<Integer> absenteesNumbers );
}
