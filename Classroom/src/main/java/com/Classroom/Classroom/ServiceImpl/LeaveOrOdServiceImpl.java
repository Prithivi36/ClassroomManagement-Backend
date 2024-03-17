package com.Classroom.Classroom.ServiceImpl;

import com.Classroom.Classroom.Entity.LeaveOrOdRequestEntity;
import com.Classroom.Classroom.Entity.StudentInfo;
import com.Classroom.Classroom.Repository.LeaveOrOdRepo;
import com.Classroom.Classroom.Repository.StudentRepository;
import com.Classroom.Classroom.Service.LeaveOrOdService;
import com.Classroom.Classroom.dto.LeaveOrOdRequestDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
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
        leaveOrOdRepo.save(leaveOrOdRequest);

        int requestedStudent=leaveOrOdRequestDto.getStudentId();
        System.out.println(requestedStudent);
        System.out.println(unqiueConstraint);
        StudentInfo foundStudent=studentRepository.findByRegNo(requestedStudent).get();
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
        leaveOrOdRepo.deleteById(requestId);
        return "Successfully deleted";
    }

    @Override
    public String acceptOrDeclineRequest(Long requestId) {
        LeaveOrOdRequestEntity leaveOrOdRequestEntity=leaveOrOdRepo.findById(requestId).get();
        leaveOrOdRequestEntity.setStatus(!leaveOrOdRequestEntity.getStatus());
        leaveOrOdRepo.save(leaveOrOdRequestEntity);
        return "Toggled";
    }
}
