package com.Classroom.Classroom.Controller;

import com.Classroom.Classroom.Service.AbsentService;
import com.Classroom.Classroom.dto.ListDto;
import com.Classroom.Classroom.dto.StudentDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/absent")
public class AbsentController {

    private AbsentService absentService;

    @PostMapping("/send")
    public String studentAbsentMarkdown(@RequestBody ListDto<Long> ListDto){
        List<Long> reg=ListDto.getIncomingList();
        return absentService.markDownAbsent(reg);
    }

    @GetMapping("on/{date}")
    public List<StudentDto> getStudentsOnDate(@PathVariable  LocalDate date){
        return absentService.getAbsentsOnDate(date);
    }

    @GetMapping("on/{date}/{hour}")
    public List<StudentDto> getStudentsOnDateHour(@PathVariable  LocalDate date,@PathVariable int hour){
        return absentService.getAbsentOnDateAndHour(date,hour);
    }
}
