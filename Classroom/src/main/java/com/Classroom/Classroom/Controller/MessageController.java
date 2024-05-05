package com.Classroom.Classroom.Controller;

import com.Classroom.Classroom.Service.MessengerClass;
import com.Classroom.Classroom.dto.ListDto;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class MessageController {

    MessengerClass messengerClass;
    AbsentController absentController;

    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    @PostMapping("/message")
    public String sendMessage(@RequestBody ListDto<Long> listDto) {
        String abs=absentController.studentAbsentMarkdown(listDto);
        return  messengerClass.sendMessage(listDto.getIncomingList());
    }
}
