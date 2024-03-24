package com.Classroom.Classroom.Security.Controller;

import com.Classroom.Classroom.Security.Register.RegisterServiceImpl;
import com.Classroom.Classroom.Security.Register.RequestDto;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/auth/register")
public class RegisterController {

    private RegisterServiceImpl registerService;

    @PostMapping("/student")
    public String newRegistration(@RequestBody RequestDto requestDto){
        return registerService.registerNewUser(requestDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/teacher")
    public String newTeacherRegistration(@RequestBody RequestDto requestDto){
        return registerService.registerNewTeacher(requestDto);
    }
}
