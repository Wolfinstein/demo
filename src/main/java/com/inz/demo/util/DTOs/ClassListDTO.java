package com.inz.demo.util.DTOs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClassListDTO {
    private Long id;
    private Long preceptorId;
    private String fullName;
    private String sign;
    private int year;
    private int studentsAmount;
    private int subjectsAmount;
}
