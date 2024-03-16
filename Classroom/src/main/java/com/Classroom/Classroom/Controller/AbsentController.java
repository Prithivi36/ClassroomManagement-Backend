package com.Classroom.Classroom.Controller;

import com.Classroom.Classroom.Service.AbsentService;
import com.Classroom.Classroom.dto.LeaveListDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class AbsentController {

    private AbsentService absentService;

    @PostMapping("/abs")
    public String studentAbsentMarkdown(@RequestBody LeaveListDto leaveListDto){
        List<Integer> reg=leaveListDto.getIncomingList();
        return absentService.markDownAbsent(reg);
    }
}
