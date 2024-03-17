package com.Classroom.Classroom.Service;

import com.Classroom.Classroom.dto.LeaveOrOdRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LeaveOrOdService {

    String raiseRequest(LeaveOrOdRequestDto leaveOrOdRequestDto);
    List<LeaveOrOdRequestDto> getAllLeaveRequest();
    String deleteRequest(Long reqestId);
    String acceptOrDeclineRequest(Long requestId);


}
