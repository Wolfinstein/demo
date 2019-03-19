package com.inz.demo.util.DTOs;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class StudentAbsencesDTO {
    private Date date;
    private String subjectName;
    private String lessonTopic;
    private String teacher;
    private String fullName;
}
