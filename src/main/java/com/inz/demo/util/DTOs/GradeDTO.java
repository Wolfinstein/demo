package com.inz.demo.util.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GradeDTO {
    private Long teacherId;
    private Long studentId;
    private Long subjectId;
    private Double gradeValue;
}
