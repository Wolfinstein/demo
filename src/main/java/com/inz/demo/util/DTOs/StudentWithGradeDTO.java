package com.inz.demo.util.DTOs;

import com.inz.demo.domain.Grade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentWithGradeDTO {

    private Long studentId;
    private String name;
    private String surname;
    private List<Grade> grades;
    private String average;
}
