package com.Classroom.Classroom.Service;

import com.Classroom.Classroom.dto.OnDutyDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface OnDutyService {
    List<OnDutyDto> getAllOnDuty();
    OnDutyDto getOnDutyOnSpecificDate(LocalDate specificDate);
    String markDownOnDuty(List<Integer> onDutyNumbers );
}
