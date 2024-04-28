package com.Classroom.Classroom.Controller;

import com.Classroom.Classroom.Service.MessengerClass;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class MessageController {

    MessengerClass messengerClass;

    @PreAuthorize("hasAnyRole('ADMIN','STUDENT','REP')")
    @PatchMapping("/message")
    public String sendMessage() {
        return  messengerClass.sendMessage("hi");
    }
}
