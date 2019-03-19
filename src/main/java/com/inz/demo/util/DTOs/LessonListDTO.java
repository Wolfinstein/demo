package com.inz.demo.util.DTOs;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class LessonListDTO {

    private String topic;
    private Date date;
    private String subjectName;
    private Long id;
}
