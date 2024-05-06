package com.Classroom.Classroom.ExternalAPI;

import com.Classroom.Classroom.Security.LoginServices.LoginDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class ExternalApiService {

    RestTemplate restTemplate;

    public String GetApi(){
        LoginDto loginDto=new LoginDto("710722243036","Prithivi");

        String tr= restTemplate.exchange("https://dummyjson.com/products", HttpMethod.GET, new ResponseEntity<>(HttpStatus.OK), String.class).getBody();
        System.out.println(tr);

        return tr;
    }
}
