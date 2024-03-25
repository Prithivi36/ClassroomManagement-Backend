package com.Classroom.Classroom.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class APIException extends RuntimeException{
    private HttpStatus status;
    private String message;
}
