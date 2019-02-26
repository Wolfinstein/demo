package com.inz.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Table(name = "class")
@Entity
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer class_id;

    @Column(name = "class_teacher")
    private Integer class_teacher;

    @Column(name = "class_year")
    private Integer class_year;

    @Column(name = "class_sign", length = 1)
    private String class_sign;

    @JsonIgnore
    @OneToMany(mappedBy = "user_std_class")
    private List<Users> users;

    @JsonIgnore
    @OneToMany(mappedBy = "lesson_class")
    private List<Lessons> lessons;

    @JsonIgnore
    @OneToMany(mappedBy = "dict_class_id")
    private List<Dictionary> dict_class_id;

    public Class() {
    }

    public Class(Integer class_teacher, Integer class_year, String class_sign) {
        this.class_teacher = class_teacher;
        this.class_year = class_year;
        this.class_sign = class_sign;
    }

    @Override
    public String toString() {
        return "Class{" +
                "class_id=" + class_id +
                ", class_teacher=" + class_teacher +
                ", class_year=" + class_year +
                ", class_sign='" + class_sign + '\'' +
                '}';
    }

    public Integer getClass_id() {
        return class_id;
    }

    public void setClass_id(Integer class_id) {
        this.class_id = class_id;
    }

    public Integer getClass_teacher() {
        return class_teacher;
    }

    public void setClass_teacher(Integer class_teacher) {
        this.class_teacher = class_teacher;
    }

    public Integer getClass_year() {
        return class_year;
    }

    public void setClass_year(Integer class_year) {
        this.class_year = class_year;
    }

    public String getClass_sign() {
        return class_sign;
    }

    public void setClass_sign(String class_sign) {
        this.class_sign = class_sign;
    }
}
