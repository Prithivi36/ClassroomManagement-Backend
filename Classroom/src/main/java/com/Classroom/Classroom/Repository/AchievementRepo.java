package com.Classroom.Classroom.Repository;

import com.Classroom.Classroom.Entity.Achievements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AchievementRepo extends JpaRepository<Achievements,Long> {
}
