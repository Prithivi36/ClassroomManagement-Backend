package com.Classroom.Classroom.Security.LoginServices;

import com.Classroom.Classroom.Security.JWTS.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {
    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;

    public String LoginToken(LoginDto loginDto){

        Authentication authentication= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginDto.getUsername(),
                        loginDto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(
                authentication
        );

        return jwtUtils.getJwtsToken(authentication);

    }
}
