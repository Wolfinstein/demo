package com.inz.demo.util.model.session;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SessionItem {
    private String token;
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private boolean isUserTeacher;
    private boolean isUserParent;
    private boolean isUserStudent;
}
