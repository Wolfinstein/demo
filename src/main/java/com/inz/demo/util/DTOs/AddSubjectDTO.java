package com.inz.demo.util.DTOs;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class AddSubjectDTO {

    private Long id;
    private String name;
    private Long teacherId;
    private Long classId;
    private int day;
    private int hour;
}
