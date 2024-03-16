package com.Classroom.Classroom.Repository;

import com.Classroom.Classroom.Entity.StudentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentInfo,Long> {
    Optional<StudentInfo> findByRegNo(int regNo);
}
