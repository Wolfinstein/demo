package com.inz.demo.domain;

import com.inz.demo.domain.enums.PresenceType;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "presences")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Presence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long presenceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher")
    private User teacher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student")
    private User student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson")
    private Lesson lesson;

    @Column(name = "presence_modification", length = 50)
    private Date date;

    @Column(name = "presence_type")
    private PresenceType type;
}
