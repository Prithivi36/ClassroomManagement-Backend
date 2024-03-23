package com.Classroom.Classroom.Service;

import com.Classroom.Classroom.dto.StudentAbsentDto;
import com.Classroom.Classroom.dto.StudentDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface AbsentService {
    List<StudentAbsentDto> getAllAbsentDetails();

    List<StudentDto> getAbsentsOnDate(LocalDate specificDate);

    List<StudentDto> getAbsentOnDateAndHour(LocalDate specificDate, int hour);
    String markDownAbsent(List<Long> absenteesNumbers );
}
