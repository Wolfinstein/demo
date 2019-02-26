package com.inz.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;

@Table(name = "absences")
@Entity
public class Absences {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer abs_id;

    @Column(name = "abs_teacher")
    private Integer abs_teacher;

    @JoinColumn(name = "abs_student")
    @ManyToOne(fetch = FetchType.LAZY)
    private Users abs_student;

    @Nullable
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "abs_date", length = 50)
    private Date abs_date;

    @Column(name = "abs_hour")
    private Integer abs_hour;

    @Column(name = "abs_subject", length = 50)
    private String abs_subject;

    @Column(name = "abs_type")
    private Integer abs_type;

    public Absences() {
    }

    public Absences(Integer abs_teacher, Users abs_student, @Nullable Date abs_date, Integer abs_hour, String abs_subject, Integer abs_type) {
        this.abs_teacher = abs_teacher;
        this.abs_student = abs_student;
        this.abs_date = abs_date;
        this.abs_hour = abs_hour;
        this.abs_subject = abs_subject;
        this.abs_type = abs_type;
    }

    @Override
    public String toString() {
        return "Absences{" +
                "abs_id=" + abs_id +
                ", abs_teacher=" + abs_teacher +
                ", abs_student=" + abs_student +
                ", abs_date=" + abs_date +
                ", abs_hour=" + abs_hour +
                ", abs_subject='" + abs_subject + '\'' +
                ", abs_type=" + abs_type +
                '}';
    }

    public Integer getAbs_id() {
        return abs_id;
    }

    public void setAbs_id(Integer abs_id) {
        this.abs_id = abs_id;
    }

    public Integer getAbs_teacher() {
        return abs_teacher;
    }

    public void setAbs_teacher(Integer abs_teacher) {
        this.abs_teacher = abs_teacher;
    }

    public Users getAbs_student() {
        return abs_student;
    }

    public void setAbs_student(Users abs_student) {
        this.abs_student = abs_student;
    }

    @Nullable
    public Date getAbs_date() {
        return abs_date;
    }

    public void setAbs_date(@Nullable Date abs_date) {
        this.abs_date = abs_date;
    }

    public Integer getAbs_hour() {
        return abs_hour;
    }

    public void setAbs_hour(Integer abs_hour) {
        this.abs_hour = abs_hour;
    }

    public String getAbs_subject() {
        return abs_subject;
    }

    public void setAbs_subject(String abs_subject) {
        this.abs_subject = abs_subject;
    }

    public Integer getAbs_type() {
        return abs_type;
    }

    public void setAbs_type(Integer abs_type) {
        this.abs_type = abs_type;
    }
}
