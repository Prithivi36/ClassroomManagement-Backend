package com.Classroom.Classroom.ServiceImpl;

import com.Classroom.Classroom.Entity.StudentAbsent;
import com.Classroom.Classroom.Entity.StudentInfo;
import com.Classroom.Classroom.Repository.StudentAbsentRepo;
import com.Classroom.Classroom.Repository.StudentRepository;
import com.Classroom.Classroom.Service.AbsentService;
import com.Classroom.Classroom.dto.StudentAbsentDto;
import com.Classroom.Classroom.dto.StudentDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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

        StudentAbsent absentOnDate=studentAbsentRepo.findByDate(specificDate).get();
        List<StudentInfo> foundStudents=absentOnDate.getStudentInfos();
        return foundStudents.stream().map((std)->modelMapper.map(std, StudentDto.class)).toList();

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
            return 8;
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

            StudentInfo foundStudent=studentRepository.findByRegNo(studentNumber).get();
            foundStudent.getAbsentList().add(foundDate);
            studentRepository.save(foundStudent);

        }
        return "MarKed Down";
    }
}
