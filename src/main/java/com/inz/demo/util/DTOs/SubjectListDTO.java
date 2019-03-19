package com.inz.demo.util.DTOs;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SubjectListDTO {

    private Long id;
    private String name;
    private String classSign;
    private int classYear;
    private String classStart;
    private int lessonCounter;
    private int kidsCounter;
}
