package com.Classroom.Classroom.Repository;

import com.Classroom.Classroom.Entity.StudentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentInfo,Long> {
}
