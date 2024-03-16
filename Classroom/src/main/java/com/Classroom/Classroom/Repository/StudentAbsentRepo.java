package com.Classroom.Classroom.Repository;

import com.Classroom.Classroom.Entity.StudentAbsent;
import com.Classroom.Classroom.dto.StudentAbsentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface StudentAbsentRepo extends JpaRepository<StudentAbsent,Long> {
    Optional<StudentAbsent> findByDate(LocalDate date);
}
