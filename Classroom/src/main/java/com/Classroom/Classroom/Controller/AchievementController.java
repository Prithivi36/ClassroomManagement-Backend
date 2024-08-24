package com.Classroom.Classroom.Controller;

import com.Classroom.Classroom.Entity.Achievements;
import com.Classroom.Classroom.Service.AchievementsService;
import com.Classroom.Classroom.dto.AchievementDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
public class AchievementController {
    AchievementsService achievementsService;
    ModelMapper modelMapper;

    @PostMapping("/achv/{id}/add")
    public String saveAchievements(@RequestBody Achievements achievements,@PathVariable("id") Long id){
        return achievementsService.addAchievements(achievements,id);
    }

    @GetMapping("/achv/getAll")
    public List<AchievementDto> getAllAchievements(){
        return achievementsService.allAchievements().stream().map((x)->modelMapper.map(x, AchievementDto.class)).toList();
    }
}
