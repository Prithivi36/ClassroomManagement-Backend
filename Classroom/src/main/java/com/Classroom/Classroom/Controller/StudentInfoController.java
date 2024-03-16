package com.Classroom.Classroom.Controller;

import com.Classroom.Classroom.Service.StudentInfoService;
import com.Classroom.Classroom.dto.StudentAbsentDto;
import com.Classroom.Classroom.dto.StudentDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
public class StudentInfoController {

    private StudentInfoService studentInfoService;

    @PostMapping("/new")
    public String studentRegistration(@RequestBody StudentDto studentDto){
        return studentInfoService.registerNewStudent(studentDto);
    }

    @GetMapping("/getAll")
    public List<StudentDto> grtAllStudent(){
        return studentInfoService.getAllStudentsInfo();
    }

    @GetMapping("/get/{regNo}")
    public StudentDto getSpecificStud(@PathVariable("regNo") int regNo){
        return studentInfoService.getSpecificStudent(regNo);
    }
    @GetMapping("/abs/{regNo}")
    public StudentAbsentDto getStudAbsOnDate(@PathVariable("regNo") int regNo){
        return studentInfoService.getStudentAbsent(regNo);
    }
}
