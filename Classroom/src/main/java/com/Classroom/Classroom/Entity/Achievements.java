package com.Classroom.Classroom.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Achievements {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String base64Img;
    private Long likes;
    @OneToMany
    private List<Comments> comments;
    private Long points;
    private String description;
    @ManyToOne()
    @JsonIgnore
    private StudentInfo studentInfo;
}
