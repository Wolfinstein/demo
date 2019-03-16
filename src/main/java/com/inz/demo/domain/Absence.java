package com.inz.demo.domain;

import com.inz.demo.domain.enums.AbsenceType;
import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "absences")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Absence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long absenceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher")
    private User teacher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student")
    private User student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson")
    private Lesson lesson;

    @Column(name = "abs_date", length = 50)
    private Date date;

    @Column(name = "abs_type")
    private AbsenceType type;
}
