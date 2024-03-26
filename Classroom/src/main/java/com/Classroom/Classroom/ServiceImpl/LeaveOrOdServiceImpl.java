package com.Classroom.Classroom.ServiceImpl;

import com.Classroom.Classroom.Entity.LeaveOrOdRequestEntity;
import com.Classroom.Classroom.Entity.StudentInfo;
import com.Classroom.Classroom.Exception.APIException;
import com.Classroom.Classroom.Repository.LeaveOrOdRepo;
import com.Classroom.Classroom.Repository.StudentRepository;
import com.Classroom.Classroom.Service.LeaveOrOdService;
import com.Classroom.Classroom.dto.LeaveOrOdRequestDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class LeaveOrOdServiceImpl implements LeaveOrOdService {
    private StudentRepository studentRepository;
    private LeaveOrOdRepo leaveOrOdRepo;
    private ModelMapper modelMapper;
    @Override
    public String raiseRequest(LeaveOrOdRequestDto leaveOrOdRequestDto) {
        LeaveOrOdRequestEntity leaveOrOdRequest=modelMapper.map(leaveOrOdRequestDto,LeaveOrOdRequestEntity.class);
        String unqiueConstraint=leaveOrOdRequestDto.getDate().toString()+leaveOrOdRequestDto.getStudentId();
        leaveOrOdRequest.setLeaveRequestId(unqiueConstraint);
        leaveOrOdRequest.setStatus(false);
        leaveOrOdRequest.setDenied(false);
        if(leaveOrOdRepo.findByLeaveRequestId(unqiueConstraint).isPresent()){
            throw new APIException(HttpStatus.BAD_REQUEST,"Already Requested on this day");
        }
        leaveOrOdRepo.save(leaveOrOdRequest);

        Long requestedStudent=leaveOrOdRequestDto.getStudentId();
        StudentInfo foundStudent=studentRepository.findByRegNo(requestedStudent).orElseThrow(()->new APIException(HttpStatus.NOT_FOUND,"Student Not Found"));
        List<LeaveOrOdRequestEntity> listOfRequest=foundStudent.getLeaveOrOdRequests();
        listOfRequest.add(leaveOrOdRepo.findByLeaveRequestId(unqiueConstraint).get());
        studentRepository.save(foundStudent);
        return "SuccessFully Raised";
    }

    @Override
    public List<LeaveOrOdRequestDto> getAllLeaveRequest() {
        List<LeaveOrOdRequestEntity> allRequest=leaveOrOdRepo.findAll();
        return allRequest.stream().map((req)->modelMapper.map(req,LeaveOrOdRequestDto.class)).toList();
    }

    @Override
    public String deleteRequest(Long requestId) {
        LeaveOrOdRequestEntity leaveOrOdRequest=leaveOrOdRepo.findById(requestId).orElseThrow(()->new APIException(HttpStatus.NOT_FOUND,"No Request Found"));
        leaveOrOdRequest.setDenied(true);
        leaveOrOdRepo.save(leaveOrOdRequest);
        return "Successfully denied";
    }

    @Override
    public String acceptOrDeclineRequest(Long requestId) {
        LeaveOrOdRequestEntity leaveOrOdRequestEntity=leaveOrOdRepo.findById(requestId).orElseThrow(()->new APIException(HttpStatus.NOT_FOUND,"No Request Found"));
        leaveOrOdRequestEntity.setStatus(!leaveOrOdRequestEntity.getStatus());
        leaveOrOdRepo.save(leaveOrOdRequestEntity);
        return "Toggled";
    }

    @Override
    public List<LeaveOrOdRequestDto> getLeaveRequestByRegNo(Long regNo, LocalDate date) {
        List<LeaveOrOdRequestEntity> requests=leaveOrOdRepo.findByStudentIdAndAndDate(regNo,date).get();
        return requests.stream().map((req)->modelMapper.map(req,LeaveOrOdRequestDto.class)).toList();
    }
}
