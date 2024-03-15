package com.Classroom.Classroom.Repository;

import com.Classroom.Classroom.Entity.StudentAbsent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentAbsentRepo extends JpaRepository<StudentAbsent,Long> {
}
