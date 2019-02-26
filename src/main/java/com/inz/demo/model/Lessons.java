package com.inz.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Table(name = "lessons")
@Entity
public class Lessons {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer lesson_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_class")
    private Class lesson_class;

    @Column(name = "lesson_day")
    private Integer lesson_day;

    @Column(name = "lesson_hour")
    private Integer lesson_hour;

    @Column(name = "lesson_subject", length = 50)
    private String lesson_subject;

    public Lessons() {
    }

    public Lessons(Class lesson_class, Integer lesson_day, Integer lesson_hour, String lesson_subject) {
        this.lesson_class = lesson_class;
        this.lesson_day = lesson_day;
        this.lesson_hour = lesson_hour;
        this.lesson_subject = lesson_subject;
    }

    @Override
    public String toString() {
        return "Lessons{" +
                "lesson_id=" + lesson_id +
                ", lesson_class=" + lesson_class +
                ", lesson_day=" + lesson_day +
                ", lesson_hour=" + lesson_hour +
                ", lesson_subject='" + lesson_subject + '\'' +
                '}';
    }

    public Integer getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(Integer lesson_id) {
        this.lesson_id = lesson_id;
    }

    public Class getLesson_class() {
        return lesson_class;
    }

    public void setLesson_class(Class lesson_class) {
        this.lesson_class = lesson_class;
    }

    public Integer getLesson_day() {
        return lesson_day;
    }

    public void setLesson_day(Integer lesson_day) {
        this.lesson_day = lesson_day;
    }

    public Integer getLesson_hour() {
        return lesson_hour;
    }

    public void setLesson_hour(Integer lesson_hour) {
        this.lesson_hour = lesson_hour;
    }

    public String getLesson_subject() {
        return lesson_subject;
    }

    public void setLesson_subject(String lesson_subject) {
        this.lesson_subject = lesson_subject;
    }
}