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
    private User teacher; // wychowawca

    @Column(name = "class_year")
    private int classYear;

    @Column(name = "class_sign", length = 10)
    private String classSign;

    @OneToMany(mappedBy = "userClass", fetch = FetchType.LAZY)
    private List<User> users = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "lessonClass")
    private List<Lesson> lessons;
}
