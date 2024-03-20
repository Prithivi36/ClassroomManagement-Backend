package com.Classroom.Classroom.Entity;

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
@Table(name = "studentDetails")
public class StudentInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long regNo;
    private String studentName;
    private String branch;
    private Long phone;
    private String mail;
    private String linkedin;
    private String instagram;
    private Long fatherNumber;
    private Long motherNumber;
    private String bloodGroup;
    private int cgpa;
    private int studentConcern;
    private String password;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(
            name = "studentOnduty",
            joinColumns = @JoinColumn(name ="studentId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name ="odId", referencedColumnName = "id")
    )
    private List<OnDutyEntity> onDutyEntities;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(
            name = "studentAbsent",
            joinColumns = @JoinColumn(name ="studentId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name ="absentId", referencedColumnName = "id")
    )
    private List<StudentAbsent> absentList;

    @OneToMany(cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
    @JoinColumn(name ="student", referencedColumnName = "id")
    private List<LeaveOrOdRequestEntity> leaveOrOdRequests;
}
