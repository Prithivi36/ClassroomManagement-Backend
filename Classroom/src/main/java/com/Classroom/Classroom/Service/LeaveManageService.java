package com.Classroom.Classroom.Service;

import com.Classroom.Classroom.dto.LeaveRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface LeaveManageService {

    String raiseRequest(LeaveRequestDto leaveRequestDto);
    String acceptOrDeclineRequest(Long requestId);
    String deleteRequest(Long reqestId);

}
