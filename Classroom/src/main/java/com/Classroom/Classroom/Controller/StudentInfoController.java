package com.Classroom.Classroom.Controller;

import com.Classroom.Classroom.ExternalAPI.ExternalApiService;
import com.Classroom.Classroom.Service.StudentInfoService;
import com.Classroom.Classroom.dto.OnDutyDto;
import com.Classroom.Classroom.dto.StudentAbsentDto;
import com.Classroom.Classroom.dto.StudentDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/student")
public class StudentInfoController {

    private StudentInfoService studentInfoService;

    @PreAuthorize("permitAll()")
    @PostMapping("/new")
    public String studentRegistration(@RequestBody StudentDto studentDto){
        return studentInfoService.registerNewStudent(studentDto);
    }

    //For Dev Test
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/newList")
    public String getMultiple(@RequestBody List<StudentDto> studentDtos){
        for(StudentDto i : studentDtos){
             studentInfoService.registerNewStudent(i);
        }
        return "Success";
    }


    @PreAuthorize("hasAnyRole('TEACHER','REP')")
    @GetMapping("/getAll")
    public List<StudentDto> grtAllStudent(){
        return studentInfoService.getAllStudentsInfo();
    }

    @PreAuthorize("hasAnyRole('TEACHER','STUDENT')")
    @GetMapping("/get/{regNo}")
    public StudentDto getSpecificStud(@PathVariable("regNo") Long regNo){
        return studentInfoService.getSpecificStudent(regNo);
    }

    @PreAuthorize("hasAnyRole('TEACHER','STUDENT')")
    @GetMapping("/ab/{regNo}")
    public List<StudentAbsentDto> getStudAbsOnDate(@PathVariable("regNo") Long regNo){
        return studentInfoService.getStudentAbsent(regNo);
    }

    @PreAuthorize("hasAnyRole('TEACHER','STUDENT')")
    @GetMapping("/od/{regNo}")
    public List<OnDutyDto> getStudOnDuty(@PathVariable("regNo") Long regNo){
        return studentInfoService.getStudentOnDuty(regNo);
    }

    @PreAuthorize("hasAnyRole('TEACHER','STUDENT')")
    @PostMapping("/skill/add/{id}/{skills}")
    public String postSkills(@PathVariable Long id,@PathVariable String skills){
        return studentInfoService.addSkills(skills,id);
    }

    @PreAuthorize("hasRole('TEACHER')")
    @PatchMapping("/merit/inc/{regNo}")
    public String increaseMerit(@PathVariable("regNo") Long regNo){
        return studentInfoService.increaseCount(regNo);
    }
    @PreAuthorize("hasRole('TEACHER')")
    @PatchMapping("/merit/dec/{regNo}")
    public String decreaseMerit(@PathVariable("regNo") Long regNo){
        return studentInfoService.decreaseCount(regNo);
    }
}
