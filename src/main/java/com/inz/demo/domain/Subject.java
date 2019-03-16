package com.inz.demo.domain;

import lombok.*;

import javax.persistence.*;
import java.sql.Time;
import java.util.List;

@Entity
@Table(name = "subjects")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    private Long subjectId;

    @Column(name = "name")
    private String name;

    @JoinColumn(name = "teacher")
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    private User teacher;

    @ManyToOne(targetEntity = Class.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "class")
    private Class subjectClass;

    @Column(name = "subject_date")
    private String subjectTimeStart;

    @Column(name = "subject_date")
    private String subjectTimeEnd;

    @OneToMany(mappedBy = "lessonClass")
    private List<Lesson> lessons;
}
