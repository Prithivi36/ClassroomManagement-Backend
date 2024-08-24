package com.Classroom.Classroom.ServiceImpl;

import com.Classroom.Classroom.Entity.Achievements;
import com.Classroom.Classroom.Exception.APIException;
import com.Classroom.Classroom.Repository.AchievementRepo;
import com.Classroom.Classroom.Repository.StudentRepository;
import com.Classroom.Classroom.Service.AchievementsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AchievementsServiceImpl implements AchievementsService {

    AchievementRepo achievementRepo;
    StudentRepository studentRepository;

    @Override
    public String addAchievements(Achievements achievements,Long id) {
        achievements.setStudentInfo(studentRepository.findByRegNo(id)
                .orElseThrow(()->new APIException(HttpStatus.NOT_FOUND,"Student Does Not exist")));
        achievements.setLikes(0L);
        achievements.setPoints(10L);
        achievementRepo.save(achievements);
        return "Saved Successfully";
    }

    @Override
    public List<Achievements> allAchievements() {
        return achievementRepo.findAll();
    }
}
