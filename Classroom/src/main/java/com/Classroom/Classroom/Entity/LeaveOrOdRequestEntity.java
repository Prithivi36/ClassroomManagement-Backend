package com.Classroom.Classroom.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "permissionRequests")
public class LeaveOrOdRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private Long studentId;
    private String studentName;
    private LocalDate date;
    private String reason;
    private Boolean status;
    private String leaveRequestId;


}
