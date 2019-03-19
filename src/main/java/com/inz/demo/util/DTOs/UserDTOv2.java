package com.inz.demo.util.DTOs;


import com.inz.demo.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTOv2 {

    private String name;
    private String surname;
    private String role;
    private Boolean isUserParent;
    private Boolean isUserStudent;
    private Boolean isUserTeacher;
    private Date birthDate;
    private Date modificationDate;
    private String classSign;
    private String email;
    private String phone;
    private List<User> relatives;
}
