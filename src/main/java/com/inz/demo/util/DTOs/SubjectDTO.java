package com.inz.demo.util.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDTO {

    private String name;
    private Long teacherId;
    private String subjectTimeStart;
    private String subjectTimeEnd;
}
