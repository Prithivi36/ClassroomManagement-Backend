package com.Classroom.Classroom.Controller;

import com.Classroom.Classroom.Service.LeaveOrOdService;
import com.Classroom.Classroom.dto.LeaveOrOdRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/request")
public class LeaveOrOdController {

    private LeaveOrOdService leaveOrOdService;

    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping("/raise")
    public String raiseLeaveOrOdRequest(@RequestBody LeaveOrOdRequestDto leaveOrOdRequestDto){
        return leaveOrOdService.raiseRequest(leaveOrOdRequestDto);
    }

    @PreAuthorize("hasAnyRole('TEACHER','REP')")
    @GetMapping("/all")
    public List<LeaveOrOdRequestDto> getAllRequests(){
        return leaveOrOdService.getAllLeaveRequest();
    }

    @PreAuthorize("hasRole('TEACHER')")
    @DeleteMapping("/del/{id}")
    public String deleteRequest(@PathVariable Long id){
        return leaveOrOdService.deleteRequest(id);
    }

    @PreAuthorize("hasRole('TEACHER')")
    @PatchMapping("/toggle/{id}")
    public String acceptOrReject(@PathVariable Long id){
        return leaveOrOdService.acceptOrDeclineRequest(id);
    }

    @PreAuthorize("hasAnyRole('TEACHER','STUDENT')")
    @GetMapping("/get/{id}/{date}")
    public List<LeaveOrOdRequestDto> getRequestByRegNo(@PathVariable Long id , @PathVariable LocalDate date){
        return leaveOrOdService.getLeaveRequestByRegNo(id,date);
    }
}
