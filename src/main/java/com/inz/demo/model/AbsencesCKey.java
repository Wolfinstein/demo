package com.inz.demo.model;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class AbsencesCKey implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "absc_student")
    private Users absc_student;

    @Column(name = "absc_type", nullable = false)
    private Integer absc_type;

}
