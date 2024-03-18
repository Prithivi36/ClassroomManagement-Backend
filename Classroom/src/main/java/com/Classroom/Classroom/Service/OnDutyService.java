package com.Classroom.Classroom.Service;

import com.Classroom.Classroom.dto.OnDutyDto;
import com.Classroom.Classroom.dto.StudentDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface OnDutyService {
    List<OnDutyDto> getAllOnDuty();
    List<StudentDto> getOnDutyOnSpecificDate(LocalDate specificDate);
    String markDownOnDuty(List<Long> onDutyNumbers );
}
