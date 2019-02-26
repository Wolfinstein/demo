package com.inz.demo.model;

import javax.persistence.*;

@Table(name = "notification")
@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "note_id")
    private Integer note_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "note_user_id")
    private Users note_user_id;

    @Column(name = "note_text", length = 256)
    private String user_password;

    @Column(name = "note_read_flag")
    private Boolean note_read_flag;

    public Notification() {
    }

    public Notification(Users note_user_id, String user_password, Boolean note_read_flag) {
        this.note_user_id = note_user_id;
        this.user_password = user_password;
        this.note_read_flag = note_read_flag;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "note_id=" + note_id +
                ", note_user_id=" + note_user_id +
                ", user_password='" + user_password + '\'' +
                ", note_read_flag=" + note_read_flag +
                '}';
    }

    public Integer getNote_id() {
        return note_id;
    }

    public void setNote_id(Integer note_id) {
        this.note_id = note_id;
    }

    public Users getNote_user_id() {
        return note_user_id;
    }

    public void setNote_user_id(Users note_user_id) {
        this.note_user_id = note_user_id;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public Boolean getNote_read_flag() {
        return note_read_flag;
    }

    public void setNote_read_flag(Boolean note_read_flag) {
        this.note_read_flag = note_read_flag;
    }
}
