package com.inz.demo.model;

import javax.persistence.*;

@Table(name = "absencesc")
@Entity
public class AbsencesC {

    @EmbeddedId
    private AbsencesCKey absc_key;

    @Column(name = "absc_counter")
    private Integer absc_counter;
}
