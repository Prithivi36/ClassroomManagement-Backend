package com.Classroom.Classroom.Security.Controller;


import com.Classroom.Classroom.Security.LoginServices.LoginDto;
import com.Classroom.Classroom.Security.LoginServices.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class LoginController {

    private LoginService loginService;

    @PostMapping("/get")
    public String getToken(@RequestBody LoginDto loginDto){
        return  loginService.LoginToken(loginDto);
    }
}
