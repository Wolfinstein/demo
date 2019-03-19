package com.inz.demo.util.DTOs;

import com.inz.demo.domain.Grade;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StudentViewDTO {

    private Long id;
    private String fullName;
    private String subjectName;
    private String subjectTeacher;
    private List<Grade> grades;
    private String average;
    private String percentageOfPresences;
}
