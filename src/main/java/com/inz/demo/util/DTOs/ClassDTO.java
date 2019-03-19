package com.inz.demo.util.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassDTO {

    private Long preceptorId;
    private int year;
    private String sign;
}
