package com.Classroom.Classroom.Repository;

import com.Classroom.Classroom.Entity.LeaveOrOdRequestEntity;
import com.Classroom.Classroom.Entity.OnDutyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface LeaveOrOdRepo extends JpaRepository<LeaveOrOdRequestEntity,Long> {
    Optional<LeaveOrOdRequestEntity> findByLeaveRequestId(String uniq);

    Optional<List<LeaveOrOdRequestEntity>> findByStudentId(Long regNo);
}
