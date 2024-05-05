package com.Classroom.Classroom.Service;

import com.Classroom.Classroom.dto.ListDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MessengerClass  {
    String sendMessage(List<Long> numbers);
}
