package com.Classroom.Classroom.ServiceImpl;

import com.Classroom.Classroom.Entity.OnDutyEntity;
import com.Classroom.Classroom.Entity.StudentAbsent;
import com.Classroom.Classroom.Entity.StudentInfo;
import com.Classroom.Classroom.Repository.OnDutyEntityRepo;
import com.Classroom.Classroom.Repository.StudentRepository;
import com.Classroom.Classroom.Service.OnDutyService;
import com.Classroom.Classroom.dto.OnDutyDto;
import com.Classroom.Classroom.dto.StudentAbsentDto;
import com.Classroom.Classroom.dto.StudentDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class OnDutyServiceImpl implements OnDutyService {

    private OnDutyEntityRepo onDutyEntityRepo;
    private StudentRepository studentRepository;
    private ModelMapper modelMapper;
    @Override
    public List<OnDutyDto> getAllOnDuty() {
        return onDutyEntityRepo.findAll().stream().map((stdAbs)->modelMapper.map(stdAbs, OnDutyDto.class)).toList();
    }

    @Override
    public List<OnDutyDto> getOnDutyOnSpecificDate(LocalDate specificDate) {
        OnDutyEntity OnDutyOnDate=onDutyEntityRepo.findByDate(specificDate).get();
        List<StudentInfo> foundStudents=OnDutyOnDate.getOnDutyMembers();
        return foundStudents.stream().map((std)->modelMapper.map(std, OnDutyDto.class)).toList();
    }

    @Override
    public String markDownOnDuty(List<Integer> onDutyNumbers) {

        LocalDate today=LocalDate.now();

        for(int studentNumber : onDutyNumbers){
            OnDutyEntity foundDate=onDutyEntityRepo.findByDate(today).orElseGet(()->{
               OnDutyEntity newOnDuty=new OnDutyEntity();
               newOnDuty.setDate(today);
               return newOnDuty;
            });

            StudentInfo foundStudent=studentRepository.findByRegNo(studentNumber).get();
            foundStudent.setId(foundStudent.getId());
            foundStudent.getOnDutyEntities().add(foundDate);
            studentRepository.save(foundStudent);

        }
        return "MarKed Down";
    }
}
