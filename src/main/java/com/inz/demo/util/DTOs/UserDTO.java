package com.inz.demo.util.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String email;
    private String password;
    private String passwordRepeated;
    private String name;
    private String surname;
    private String login;
    private String role;
    private Boolean isUserTeacher;
    private Boolean isUserStudent;
    private Boolean isUserParent;
    private Date birthDate;
    private Long parentId;
    private Long classId;
    private String kidsIds;
    private String phone;
}