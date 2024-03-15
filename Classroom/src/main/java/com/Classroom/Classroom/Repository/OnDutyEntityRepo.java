package com.Classroom.Classroom.Repository;

import com.Classroom.Classroom.Entity.OnDutyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OnDutyEntityRepo extends JpaRepository<OnDutyEntity,Long> {
}
