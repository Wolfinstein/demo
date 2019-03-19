package com.inz.demo.util.DTOs;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SubjectListDTO {

    private Long id;
    private String name;
    private Long teacherId;
    private Long classId;
    private String classSign;
    private int classYear;
    private int classDay;
    private int classHour;
    private String classStart;
    private int lessonCounter;
    private int kidsCounter;
    private String teacherName;
}
