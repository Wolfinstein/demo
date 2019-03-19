package com.inz.demo.util.DTOs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KidDTO {

    private Long id;
    private String name;
}
