package com.Classroom.Classroom.ServiceImpl;

import com.Classroom.Classroom.Entity.StudentAbsent;
import com.Classroom.Classroom.Entity.StudentInfo;
import com.Classroom.Classroom.Repository.StudentAbsentRepo;
import com.Classroom.Classroom.Repository.StudentRepository;
import com.Classroom.Classroom.Service.AbsentService;
import com.Classroom.Classroom.dto.StudentAbsentDto;
import com.Classroom.Classroom.dto.StudentDto;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
        StudentAbsent absentonDate=studentAbsentRepo.findByDate(specificDate).get();
        List<StudentInfo> foundStudents=absentonDate.getAbsentList();
        return foundStudents.stream().map((std)->modelMapper.map(foundStudents, StudentDto.class)).toList();
    }

    @Override
    public String markDownAbsent(List<Integer> absenteesNumbers) {

        //today
        LocalDate today=LocalDate.now();

        StudentAbsent foundDate=studentAbsentRepo.findByDate(today).orElseGet(()->{
            StudentAbsent newStudentAbsent=new StudentAbsent();
            newStudentAbsent.setDate(today);

            return newStudentAbsent;
        });


        for(int studentNumber : absenteesNumbers){
            StudentInfo foundStudent=studentRepository.findByRegNo(studentNumber).orElse(null);
            foundStudent.getAbsentList().add(foundDate);
            studentRepository.save(foundStudent);

            foundDate.getAbsentList().add(foundStudent);
        }

        studentAbsentRepo.save(foundDate);
        //if not foundDate
//        if (foundDate == null) {
//            List<StudentInfo> addedStudents=new ArrayList<>();
//            for(int studs : absenteesNumbers){
//                addedStudents.add(studentRepository.findByRegNo(studs).get());
//            }
//            StudentAbsent cascadingAbsent=new StudentAbsent();
//            cascadingAbsent.setDate(today);
//            cascadingAbsent.setAbsentList(addedStudents);
//            studentAbsentRepo.save(cascadingAbsent);
//
//            for(int student: absenteesNumbers){
//                StudentInfo foundStudent=studentRepository.findByRegNo(student).get();
//                List<StudentAbsent> absentList=foundStudent.getAbsentList();
//
//                absentList.add(cascadingAbsent);
//
//                studentRepository.save(foundStudent);
//            }
//        }
//
//
//
//
//        //if found date
//        if(foundDate!=null) {
//            for (int person : absenteesNumbers) {
//                StudentInfo foundStudent = studentRepository.findByRegNo(person).get();
//                List<StudentAbsent> absentFound = foundStudent.getAbsentList();
//
//                absentFound.add(foundDate);
//                studentRepository.save(foundStudent);
//
//                List<StudentInfo> foundStudents = foundDate.getAbsentList();
//
//                foundStudents.add(foundStudent);
//
//                studentAbsentRepo.save(foundDate);
//            }
//        }
        return "Marekd Down";
    }
}