package com.Classroom.Classroom.Repository;

import com.Classroom.Classroom.Entity.LeaveRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveRequestEntityRepo extends JpaRepository<LeaveRequestEntity,Long> {
}
