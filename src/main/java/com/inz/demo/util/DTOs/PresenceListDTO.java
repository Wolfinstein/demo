package com.inz.demo.util.DTOs;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class PresenceListDTO {

    private String presenceType;
    private Long id;
    private String name;
    private String surname;
    private Date date;
}
