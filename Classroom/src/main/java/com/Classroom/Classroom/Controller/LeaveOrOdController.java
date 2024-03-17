package com.Classroom.Classroom.Controller;

import com.Classroom.Classroom.Service.LeaveOrOdService;
import com.Classroom.Classroom.dto.LeaveOrOdRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/request")
public class LeaveOrOdController {

    private LeaveOrOdService leaveOrOdService;

    @PostMapping("/raise")
    public String raiseLeaveOrOdRequest(@RequestBody LeaveOrOdRequestDto leaveOrOdRequestDto){
        return leaveOrOdService.raiseRequest(leaveOrOdRequestDto);
    }

    @GetMapping("/all")
    public List<LeaveOrOdRequestDto> getAllRequests(){
        return leaveOrOdService.getAllLeaveRequest();
    }

    @DeleteMapping("/del/{id}")
    public String deleteRequest(@PathVariable Long id){
        return leaveOrOdService.deleteRequest(id);
    }

    @PatchMapping("/toggle/{id}")
    public String acceptOrReject(@PathVariable Long id){
        return leaveOrOdService.acceptOrDeclineRequest(id);
    }
}
