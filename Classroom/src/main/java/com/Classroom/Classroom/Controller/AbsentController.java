package com.Classroom.Classroom.Controller;

import com.Classroom.Classroom.Service.AbsentService;
import com.Classroom.Classroom.dto.ListDto;
import com.Classroom.Classroom.dto.StudentDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
public class AbsentController {

    private AbsentService absentService;

    @PostMapping("/abs")
    public String studentAbsentMarkdown(@RequestBody ListDto leaveListDto){
        List<Integer> reg=leaveListDto.getIncomingList();
        return absentService.markDownAbsent(reg);
    }

    @GetMapping("ab/{date}")
    public List<StudentDto> getStudentsOnDate(@PathVariable  LocalDate date){
        return absentService.getAbsentsOnDate(date);
    }
}
