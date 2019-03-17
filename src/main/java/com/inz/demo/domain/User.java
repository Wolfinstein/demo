package com.inz.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_login", nullable = false, length = 30)
    private String userLogin;

    @Column(name = "user_password", nullable = false, length = 50)
    private String userPassword;

    @Column(name = "user_teacher_flag")
    private Boolean isUserTeacher;

    @Column(name = "user_student_flag")
    private Boolean isUserStudent;

    @Column(name = "user_parent_flag")
    private Boolean isUserParent;

    @Column(name = "user_name", nullable = false, length = 30)
    private String userName;

    @Column(name = "user_surname", nullable = false, length = 50)
    private String userSurname;

    @Column(name = "user_email", nullable = false, length = 50)
    private String userEmail;

    @Column(name = "user_phone", nullable = false, length = 50)
    private String phoneNumber;

    @Column(name = "user_add_date", nullable = false, length = 50)
    private Date userTimestamp;

    @Column(name = "user_birth_date")
    private Date birthDate;

    @Column(name = "user_mod_date", length = 50)
    private Date userModificationDate;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_parent_id")
    private User userParent;

    @ManyToOne(targetEntity = Class.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_class")
    private Class userClass;

    @JsonIgnore
    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<UserKid> userKids = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Notification> notifications = new ArrayList<>();

    public String getRoles() {
        if (!isUserTeacher && !isUserStudent && isUserParent) {
            return "PARENT";
        }
        if (isUserTeacher && isUserStudent && isUserParent) {
            return "ADMIN";
        }
        if (isUserTeacher && !isUserStudent && isUserParent) {
            return "TEACHER";
        }
        if (!isUserTeacher && isUserStudent && !isUserParent) {
            return "STUDENT";
        }
        return "Error";
    }

}