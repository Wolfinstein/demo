package com.inz.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "classes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classId;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "teacher")
    private User preceptor;

    @Column(name = "class_year")
    private int classYear;

    @Column(name = "class_sign", length = 2)
    private String classSign;

    @JsonIgnore
    @OneToMany(mappedBy = "userClass", fetch = FetchType.LAZY)
    private List<User> users = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "subjectClass", fetch = FetchType.LAZY)
    private List<Subject> subjects = new ArrayList<>();

}
