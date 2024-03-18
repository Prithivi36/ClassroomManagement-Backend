package com.Classroom.Classroom.Controller;

import com.Classroom.Classroom.Service.StudentInfoService;
import com.Classroom.Classroom.dto.OnDutyDto;
import com.Classroom.Classroom.dto.StudentAbsentDto;
import com.Classroom.Classroom.dto.StudentDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/student")
public class StudentInfoController {

    private StudentInfoService studentInfoService;

    @PostMapping("/new")
    public String studentRegistration(@RequestBody StudentDto studentDto){
        return studentInfoService.registerNewStudent(studentDto);
    }

    @PostMapping("/newList")
    public String getMultiple(@RequestBody List<StudentDto> studentDtos){
        for(StudentDto i : studentDtos){
             studentInfoService.registerNewStudent(i);
        }
        return "Success";
    }

    @GetMapping("/getAll")
    public List<StudentDto> grtAllStudent(){
        return studentInfoService.getAllStudentsInfo();
    }

    @GetMapping("/get/{regNo}")
    public StudentDto getSpecificStud(@PathVariable("regNo") Long regNo){
        return studentInfoService.getSpecificStudent(regNo);
    }
    @GetMapping("/ab/{regNo}")
    public List<StudentAbsentDto> getStudAbsOnDate(@PathVariable("regNo") Long regNo){
        return studentInfoService.getStudentAbsent(regNo);
    }

    @GetMapping("/od/{regNo}")
    public List<OnDutyDto> getStudOnDuty(@PathVariable("regNo") Long regNo){
        return studentInfoService.getStudentOnDuty(regNo);
    }
}
