package com.Classroom.Classroom.Security.ResetPass;

import com.Classroom.Classroom.Exception.APIException;
import com.Classroom.Classroom.Security.Database.SecurityRepo.RoleRepo;
import com.Classroom.Classroom.Security.Database.SecurityRepo.UserRepository;
import com.Classroom.Classroom.Security.Database.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ResetPasswordService {
    UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public String updatePassword(String newPassword,String userName){
        UserEntity userEntity=userRepository.findByUserName(userName).orElseThrow(()->new APIException(HttpStatus.NOT_FOUND,"User Not Found"));
        userEntity.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(userEntity);
        return "Success";
    }
}
