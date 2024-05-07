package com.Classroom.Classroom.ExternalAPI;

import com.Classroom.Classroom.Entity.StudentInfo;
import com.Classroom.Classroom.Exception.APIException;
import com.Classroom.Classroom.Repository.StudentRepository;
import com.Classroom.Classroom.Security.LoginServices.LoginDto;
import com.Classroom.Classroom.dto.StudentDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@AllArgsConstructor
public class ExternalApiService {

    RestTemplate restTemplate;
    ModelMapper modelMapper;
    StudentRepository studentRepository;

    public List<StudentDto> GetApi() throws JsonProcessingException {
        LoginDto loginDto=new LoginDto("710722243036","Prithivi");
        String response= restTemplate.exchange("http://65.0.7.62:8081/student/getAll", HttpMethod.GET, new ResponseEntity<>(HttpStatus.OK), String.class).getBody();
        System.out.println(response);

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        List<StudentDto> studentList = mapper.readValue(response, new TypeReference<List<StudentDto>>(){});

        for (StudentDto studentDto : studentList) {

            //For saving data from the server 1

//            studentRepository.save(modelMapper.map(studentDto, StudentInfo.class));
//            System.out.println("Saved");

            //checking any change in server
            StudentInfo currentStudent=studentRepository.findByRegNo(studentDto.getRegNo()).orElseThrow(()->new APIException(HttpStatus.INTERNAL_SERVER_ERROR,"Some Conflic Occured"));
            if(currentStudent.getCgpa()!= studentDto.getCgpa()){
                currentStudent.setCgpa(studentDto.getCgpa());
                studentRepository.save(currentStudent);
                System.out.println("Change Happened");
            }
        }
        return studentList;
    }
}
