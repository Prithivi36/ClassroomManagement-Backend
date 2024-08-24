package com.Classroom.Classroom.Service;

import com.Classroom.Classroom.Entity.Achievements;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AchievementsService {
    String addAchievements(Achievements achievements,Long id);
    List<Achievements> allAchievements();
}
