package com.inz.demo.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "lessons")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lessonId;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Class.class)
    @JoinColumn(name = "lesson_class")
    private Class lessonClass;

    @Column(name = "lesson_date")
    private Date date;

    @Column(name = "lesson_topic")
    private String topic;

    @JoinColumn(name = "subject")
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Subject.class)
    private Subject subject;

    @OneToMany(mappedBy = "lesson", orphanRemoval = true)
    private List<Presence> userPresences;

}