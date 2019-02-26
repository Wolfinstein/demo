package com.inz.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Table(name = "users")
    @Entity
    public class Users{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "user_id")
        private Integer user_id;

        @Column(name = "user_login", length = 30)
        private String user_login;

        @Column(name = "user_password", length = 50)
        private String user_password;

        @Column(name = "user_teacher_flag")
        private Boolean user_teacher_flag;

        @Column(name = "user_student_flag")
        private Boolean user_student_flag;

        @Column(name = "user_parent_flag")
        private Boolean user_parent_flag;

        @Column(name = "user_name", length = 30)
        private String user_name;

        @Column(name = "user_surrname", length = 50)
        private String user_surrname;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        @Column(name = "user_add_date", length = 50)
        private Date user_add_date;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        @Column(name = "user_mod_date", length = 50)
        private Date user_mod_date;

        @JsonIgnore
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_std_class")
        private Class user_std_class;

        @Nullable
        @Column(name = "user_std_parent", nullable = true)
        private Integer user_std_parent;

        @JsonIgnore
        @OneToMany(mappedBy = "note_user_id")
        private List<Notification> note_user_id;


        public Users(String user_login, String user_password, Boolean user_teacher_flag, Boolean user_student_flag, Boolean user_parent_flag, String user_name, String user_surrname, Date user_add_date, Date user_mod_date, Class user_std_class, @Nullable Integer user_std_parent) {
            this.user_login = user_login;
            this.user_password = user_password;
            this.user_teacher_flag = user_teacher_flag;
            this.user_student_flag = user_student_flag;
            this.user_parent_flag = user_parent_flag;
            this.user_name = user_name;
            this.user_surrname = user_surrname;
            this.user_add_date = user_add_date;
            this.user_mod_date = user_mod_date;
            this.user_std_class = user_std_class;
            this.user_std_parent = user_std_parent;
        }

        public Users() {
        }

        @Override
        public String toString() {
            return "Users{" +
                    "user_id=" + user_id +
                    ", user_login='" + user_login + '\'' +
                    ", user_password='" + user_password + '\'' +
                    ", user_teacher_flag=" + user_teacher_flag +
                    ", user_student_flag=" + user_student_flag +
                    ", user_parent_flag=" + user_parent_flag +
                    ", user_name='" + user_name + '\'' +
                    ", user_surrname='" + user_surrname + '\'' +
                    ", user_add_date=" + user_add_date +
                    ", user_mod_date=" + user_mod_date +
                    ", user_std_class=" + user_std_class +
                    ", user_std_parent=" + user_std_parent +
                    '}';
        }

        public Integer getUser_id() {
            return user_id;
        }

        public void setUser_id(Integer user_id) {
            this.user_id = user_id;
        }

        public String getUser_login() {
            return user_login;
        }

        public void setUser_login(String user_login) {
            this.user_login = user_login;
        }

        public String getUser_password() {
            return user_password;
        }

        public void setUser_password(String user_password) {
            this.user_password = user_password;
        }

        public Boolean getUser_teacher_flag() {
            return user_teacher_flag;
        }

        public void setUser_teacher_flag(Boolean user_teacher_flag) {
            this.user_teacher_flag = user_teacher_flag;
        }

        public Boolean getUser_student_flag() {
            return user_student_flag;
        }

        public void setUser_student_flag(Boolean user_student_flag) {
            this.user_student_flag = user_student_flag;
        }

        public Boolean getUser_parent_flag() {
            return user_parent_flag;
        }

        public void setUser_parent_flag(Boolean user_parent_flag) {
            this.user_parent_flag = user_parent_flag;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getUser_surrname() {
            return user_surrname;
        }

        public void setUser_surrname(String user_surrname) {
            this.user_surrname = user_surrname;
        }

        public Date getUser_add_date() {
            return user_add_date;
        }

        public void setUser_add_date(Date user_add_date) {
            this.user_add_date = user_add_date;
        }

        public Date getUser_mod_date() {
            return user_mod_date;
        }

        public void setUser_mod_date(Date user_mod_date) {
            this.user_mod_date = user_mod_date;
        }

        public Class getUser_std_class() {
            return user_std_class;
        }

        public void setUser_std_class(Class user_std_class) {
            this.user_std_class = user_std_class;
        }

        @Nullable
        public Integer getUser_std_parent() {
            return user_std_parent;
        }

        public void setUser_std_parent(@Nullable Integer user_std_parent) {
            this.user_std_parent = user_std_parent;
        }
    }

