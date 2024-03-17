package com.Classroom.Classroom.Controller;

import com.Classroom.Classroom.Service.OnDutyService;
import com.Classroom.Classroom.dto.ListDto;
import com.Classroom.Classroom.dto.OnDutyDto;
import com.Classroom.Classroom.dto.StudentDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/onduty")
public class OnDutyController {

    private OnDutyService onDutyService;

    @PostMapping("/send")
    public String onDutyMarkDown(@RequestBody  ListDto onDutyListDto){
        List<Integer> onDutyList=onDutyListDto.getIncomingList();
        return onDutyService.markDownOnDuty(onDutyList);
    }
    @GetMapping("on/{date}")
    public List<StudentDto> getStudentsOnDate(@PathVariable LocalDate date){
        return onDutyService.getOnDutyOnSpecificDate(date);
    }
}
