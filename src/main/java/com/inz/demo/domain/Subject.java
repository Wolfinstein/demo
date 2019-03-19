package com.inz.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
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

    @JsonIgnore
    @ManyToOne(targetEntity = Class.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "class")
    private Class subjectClass;

    @Column(name = "subject_day")
    private int subjectDay;

    @Column(name = "subject_hour")
    private int subjectHour;

    @JsonIgnore
    @OneToMany(mappedBy = "lessonClass")
    private List<Lesson> lessons;

}
