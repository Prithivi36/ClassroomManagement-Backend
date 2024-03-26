package com.Classroom.Classroom.Controller;

import com.Classroom.Classroom.Service.OnDutyService;
import com.Classroom.Classroom.dto.ListDto;
import com.Classroom.Classroom.dto.OnDutyDto;
import com.Classroom.Classroom.dto.StudentDto;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/onduty")
public class OnDutyController {

    private OnDutyService onDutyService;

    @PreAuthorize("hasAnyRole('REP','TEACHER')")
    @PostMapping("/send")
    public String onDutyMarkDown(@RequestBody  ListDto<Long> onDutyListDto){
        List<Long> onDutyList=onDutyListDto.getIncomingList();
        return onDutyService.markDownOnDuty(onDutyList);
    }
    @PreAuthorize("hasAnyRole('TEACHER','REP')")
    @GetMapping("on/{date}")
    public List<StudentDto> getStudentsOnDate(@PathVariable LocalDate date){
        return onDutyService.getOnDutyOnSpecificDate(date);
    }
}
