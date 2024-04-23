package com.Classroom.Classroom.ServiceImpl;

import com.Classroom.Classroom.Entity.OnDutyEntity;
import com.Classroom.Classroom.Entity.StudentAbsent;
import com.Classroom.Classroom.Entity.StudentInfo;
import com.Classroom.Classroom.Exception.APIException;
import com.Classroom.Classroom.Repository.OnDutyEntityRepo;
import com.Classroom.Classroom.Repository.StudentRepository;
import com.Classroom.Classroom.Service.OnDutyService;
import com.Classroom.Classroom.dto.OnDutyDto;
import com.Classroom.Classroom.dto.StudentAbsentDto;
import com.Classroom.Classroom.dto.StudentDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
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
    public List<StudentDto> getOnDutyOnSpecificDate(LocalDate specificDate) {
        OnDutyEntity OnDutyOnDate=onDutyEntityRepo.findByDate(specificDate).orElseThrow(()->new APIException(HttpStatus.NOT_FOUND,"No On Duty on this date"));
        List<StudentInfo> foundStudents=OnDutyOnDate.getOnDutyMembers();
        return foundStudents.stream().map((std)->modelMapper.map(std, StudentDto.class)).toList();
    }

    @Override
    public String markDownOnDuty(List<Long> onDutyNumbers) {

        LocalDate today=LocalDate.now();

        for(Long studentNumber : onDutyNumbers){
            OnDutyEntity foundDate=onDutyEntityRepo.findByDate(today).orElseGet(()->{
               OnDutyEntity newOnDuty=new OnDutyEntity();
               newOnDuty.setDate(today);
               return newOnDuty;
            });

            StudentInfo foundStudent=studentRepository.findByRegNo(studentNumber).orElseThrow(()->new APIException(HttpStatus.NOT_FOUND,"Student Not Found"));
            foundStudent.setId(foundStudent.getId());
            List<OnDutyEntity> foundOnDuty=foundStudent.getOnDutyEntities();
            if(foundOnDuty.contains(foundDate)){
                continue;
            }
            foundStudent.getOnDutyEntities().add(foundDate);
            studentRepository.save(foundStudent);

        }
        return "MarKed Down";
    }
}
