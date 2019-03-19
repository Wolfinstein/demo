package com.inz.demo.util.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String login;
    private Boolean isUserTeacher;
    private Boolean isUserStudent;
    private Boolean isUserParent;
    private Date birthDate;
    private Date modificationDate;
    private Long classId;
    private Long[] kidsIds;
    private String phone;
    private String classSign;
    private String role;
}