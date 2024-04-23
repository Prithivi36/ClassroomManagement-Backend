package com.Classroom.Classroom.Security.Controller;

import com.Classroom.Classroom.Security.Register.RegisterServiceImpl;
import com.Classroom.Classroom.Security.Register.RequestDto;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
//For Dev
    @PostMapping("/list/stud")
    public String newListRegistration(@RequestBody List<RequestDto> requestDto){
        for (RequestDto requestDto1 : requestDto) {
            registerService.registerNewUser(requestDto1);
        }
        return  "Success";
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/teacher")
    public String newTeacherRegistration(@RequestBody RequestDto requestDto){
        return registerService.registerNewTeacher(requestDto);
    }

    @PreAuthorize("hasRole('TEACHER')")
    @PutMapping("/rep/{id}")
    public String makeStudentRep(@PathVariable String id){
        return registerService.makeRep(id);
    }
}
