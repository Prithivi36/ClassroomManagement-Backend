package com.Classroom.Classroom.Repository;

import com.Classroom.Classroom.Entity.OnDutyEntity;
import com.Classroom.Classroom.Entity.StudentAbsent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface OnDutyEntityRepo extends JpaRepository<OnDutyEntity,Long> {
    Optional<OnDutyEntity> findByDate(LocalDate date);

}
