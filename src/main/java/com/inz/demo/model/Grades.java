package com.inz.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;

@Table(name = "grades")
@Entity
public class Grades {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer gr_id;

    @Column(name = "gr_teacher")
    private Integer gr_teacher;

    @JoinColumn(name = "gr_student")
    @ManyToOne(fetch = FetchType.LAZY)
    private Users gr_student;

    @Nullable
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "gr_mod_date", length = 50)
    private Date gr_mod_date;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "gr_add_date", length = 50)
    private Date gr_add_date;

    @Column(name = "gr_subject", length = 50)
    private String gr_subject;

    @Column(name = "gr_grade")
    private Double gr_grade;

    public Grades() {
    }

    public Grades(Integer gr_teacher, Users gr_student, @Nullable Date gr_mod_date, Date gr_add_date, String gr_subject, Double gr_grade) {
        this.gr_teacher = gr_teacher;
        this.gr_student = gr_student;
        this.gr_mod_date = gr_mod_date;
        this.gr_add_date = gr_add_date;
        this.gr_subject = gr_subject;
        this.gr_grade = gr_grade;
    }

    public Grades(Integer gr_teacher, Users gr_student, Date gr_add_date, String gr_subject, Double gr_grade) {
        this.gr_teacher = gr_teacher;
        this.gr_student = gr_student;
        this.gr_add_date = gr_add_date;
        this.gr_subject = gr_subject;
        this.gr_grade = gr_grade;
    }

    @Override
    public String toString() {
        return "Grades{" +
                "gr_id=" + gr_id +
                ", gr_teacher=" + gr_teacher +
                ", gr_student=" + gr_student +
                ", gr_mod_date=" + gr_mod_date +
                ", gr_add_date=" + gr_add_date +
                ", gr_subject='" + gr_subject + '\'' +
                ", gr_grade=" + gr_grade +
                '}';
    }

    public Integer getGr_id() {
        return gr_id;
    }

    public void setGr_id(Integer gr_id) {
        this.gr_id = gr_id;
    }

    public Integer getGr_teacher() {
        return gr_teacher;
    }

    public void setGr_teacher(Integer gr_teacher) {
        this.gr_teacher = gr_teacher;
    }

    public Users getGr_student() {
        return gr_student;
    }

    public void setGr_student(Users gr_student) {
        this.gr_student = gr_student;
    }

    @Nullable
    public Date getGr_mod_date() {
        return gr_mod_date;
    }

    public void setGr_mod_date(@Nullable Date gr_mod_date) {
        this.gr_mod_date = gr_mod_date;
    }

    public Date getGr_add_date() {
        return gr_add_date;
    }

    public void setGr_add_date(Date gr_add_date) {
        this.gr_add_date = gr_add_date;
    }

    public String getGr_subject() {
        return gr_subject;
    }

    public void setGr_subject(String gr_subject) {
        this.gr_subject = gr_subject;
    }

    public Double getGr_grade() {
        return gr_grade;
    }

    public void setGr_grade(Double gr_grade) {
        this.gr_grade = gr_grade;
    }
}
