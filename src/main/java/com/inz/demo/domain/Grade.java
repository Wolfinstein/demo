package com.inz.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "grades")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grade_id")
    private Long gradeId;

    @JsonIgnore
    @JoinColumn(name = "teacher_id")
    @ManyToOne(targetEntity = User.class)
    private User teacher;

    @JsonIgnore
    @JoinColumn(name = "student_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User student;

    @JsonIgnore
    @JoinColumn(name = "subject_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Subject subject;

    @Column(name = "grade_modification_date", length = 50)
    private Date gradeModificationDate;

    @Column(name = "grade_timestamp", length = 50)
    private Date gradeTimestamp;

    @Column(name = "grade_value")
    private Double gradeValue;
}
