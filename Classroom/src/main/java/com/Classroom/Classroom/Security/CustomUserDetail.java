package com.Classroom.Classroom.Security;

import com.Classroom.Classroom.Exception.APIException;
import com.Classroom.Classroom.Security.Database.SecurityRepo.UserRepository;
import com.Classroom.Classroom.Security.Database.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@Service
@AllArgsConstructor
public class CustomUserDetail implements UserDetailsService {

    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity currentUser=userRepository.findByUserName(username).orElseThrow(()->new APIException(HttpStatus.NOT_FOUND,"User Not Found"));

        Set<SimpleGrantedAuthority> authorities=currentUser.getRolesEntities()
                .stream().map((auth)->new SimpleGrantedAuthority(auth.getRole())).collect(Collectors.toSet());


        return new User(
                currentUser.getUserName(),
                currentUser.getPassword(),
                authorities
        );
    }
}
