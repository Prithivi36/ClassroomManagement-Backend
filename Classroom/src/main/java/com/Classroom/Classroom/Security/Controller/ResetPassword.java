package com.Classroom.Classroom.Security.Controller;

import com.Classroom.Classroom.Security.LoginServices.LoginDto;
import com.Classroom.Classroom.Security.ResetPass.ResetPasswordService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
public class ResetPassword {
    ResetPasswordService resetPasswordService;

    @PreAuthorize("hasAnyRole('STUDENT','TEACHER','REP','ADMIN')")
    @PostMapping("/reset")
    public String resetPass(@RequestBody LoginDto loginDto){
        return resetPasswordService.updatePassword( loginDto.getPassword(),loginDto.getUsername());
    }
}
