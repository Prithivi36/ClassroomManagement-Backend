package com.Classroom.Classroom.ServiceImpl;

import com.Classroom.Classroom.Entity.StudentAbsent;
import com.Classroom.Classroom.Entity.StudentInfo;
import com.Classroom.Classroom.Exception.APIException;
import com.Classroom.Classroom.Repository.StudentAbsentRepo;
import com.Classroom.Classroom.Repository.StudentRepository;
import com.Classroom.Classroom.Service.AbsentService;
import com.Classroom.Classroom.dto.StudentAbsentDto;
import com.Classroom.Classroom.dto.StudentDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AbsentServiceImpl implements AbsentService {

    private  StudentAbsentRepo studentAbsentRepo;
    private StudentRepository studentRepository;
    private ModelMapper modelMapper;
    @Override
    public List<StudentAbsentDto> getAllAbsentDetails() {
        return studentAbsentRepo.findAll().stream().map((stdAbs)->modelMapper.map(stdAbs,StudentAbsentDto.class)).toList();
    }

    @Override
    public List<StudentDto> getAbsentsOnDate(LocalDate specificDate) {
        List<StudentAbsent> absentOnDate = studentAbsentRepo.findByDate(specificDate).orElseThrow(()->new APIException(HttpStatus.NOT_FOUND,"Absents Not Found on this date"));

            Set<StudentInfo> foundStudents = absentOnDate.stream()
                    .flatMap(absent -> absent.getStudentInfos().stream())
                    .collect(Collectors.toSet());

            return foundStudents.stream()
                    .map(studentInfo -> modelMapper.map(studentInfo, StudentDto.class))
                    .toList();
    }

    @Override
    public List<StudentDto> getAbsentOnDateAndHour(LocalDate specificDate, int hour) {
        StudentAbsent absentDateHour=studentAbsentRepo.findByDateAndHour(specificDate, hour).orElseThrow(()->new APIException(HttpStatus.NOT_FOUND,"Absent Not Found"));
        List<StudentInfo> foundAbsents=absentDateHour.getStudentInfos();
        return foundAbsents.stream().map((studentInfo -> modelMapper.map(studentInfo,StudentDto.class))).toList();
    }


    public int getHour(int time, int minute) {
        if ((time == 8 && minute >= 55) || (time == 9 && minute <= 55)) {
            return 1;
        } else if ((time == 9 && minute >= 56) || (time == 10 && minute <= 51)) {
            return 2;
        } else if ((time == 11 && minute >= 11) || (time == 12 && minute <= 6)) {
            return 3;
        } else if ((time == 13 && minute >= 1) || (time == 13 && minute <= 55)) {
            return 4;
        } else if ((time == 13 && minute >= 56) || (time == 14 && minute <= 51)) {
            return 5;
        } else if ((time == 15 && minute >= 11) || (time == 16 && minute <= 6)) {
            return 6;
        } else if ((time == 16 && minute >= 7) || (time == 17 && minute <= 1)) {
            return 7;
        } else {
            return 0;
        }
    }

    @Override
    public String markDownAbsent(List<Long> absenteesNumbers) {

        LocalDate today=LocalDate.now();
        int timeNow=LocalTime.now().getHour();
        int minuteNow=LocalTime.now().getMinute();
        int currentHour=getHour(timeNow,minuteNow);

        for(Long studentNumber : absenteesNumbers){
            StudentAbsent foundDate=studentAbsentRepo.findByDateAndHour(today,currentHour).orElseGet(()->{
                StudentAbsent newStudentAbsent=new StudentAbsent();
                newStudentAbsent.setDate(today);
                newStudentAbsent.setHour(currentHour);
                return newStudentAbsent;
            });

            StudentInfo foundStudent=studentRepository.findByRegNo(studentNumber).orElseThrow(()->new APIException(HttpStatus.NOT_FOUND,"Student Not Found"));
            List<StudentAbsent> absentList=foundStudent.getAbsentList();
            if(absentList.contains(foundDate)){
                continue;
            }
            foundStudent.getAbsentList().add(foundDate);
            studentRepository.save(foundStudent);

        }
        return "MarKed Down";
    }
}
