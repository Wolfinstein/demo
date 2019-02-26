package com.inz.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;

import java.sql.Time;
import java.util.Date;

@Table(name = "log")
@Entity
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Integer log_id;

    @Column(name = "log_user_id")
    private Integer user_login;

    @Column(name = "log_table_name")
    private String log_table_name;

    @Column(name = "log_table_key")
    private Integer log_table_key;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "log_date", length = 50)
    private Date log_date;

    @JsonFormat(pattern = "HH:mm:ss")
    @Column(name = "log_time")
    private Time log_time;

    @Column(name = "log_operation", length = 10)
    private String log_operation;

    public Log() {
    }

    public Log(Integer user_login, String log_table_name, Integer log_table_key, Date log_date, Time log_time, String log_operation) {
        this.user_login = user_login;
        this.log_table_name = log_table_name;
        this.log_table_key = log_table_key;
        this.log_date = log_date;
        this.log_time = log_time;
        this.log_operation = log_operation;
    }

    @Override
    public String toString() {
        return "Log{" +
                "log_id=" + log_id +
                ", user_login=" + user_login +
                ", log_table_name='" + log_table_name + '\'' +
                ", log_table_key=" + log_table_key +
                ", log_date=" + log_date +
                ", log_time=" + log_time +
                ", log_operation='" + log_operation + '\'' +
                '}';
    }

    public Integer getLog_id() {
        return log_id;
    }

    public void setLog_id(Integer log_id) {
        this.log_id = log_id;
    }

    public Integer getUser_login() {
        return user_login;
    }

    public void setUser_login(Integer user_login) {
        this.user_login = user_login;
    }

    public String getLog_table_name() {
        return log_table_name;
    }

    public void setLog_table_name(String log_table_name) {
        this.log_table_name = log_table_name;
    }

    public Integer getLog_table_key() {
        return log_table_key;
    }

    public void setLog_table_key(Integer log_table_key) {
        this.log_table_key = log_table_key;
    }

    public Date getLog_date() {
        return log_date;
    }

    public void setLog_date(Date log_date) {
        this.log_date = log_date;
    }

    public Time getLog_time() {
        return log_time;
    }

    public void setLog_time(Time log_time) {
        this.log_time = log_time;
    }

    public String getLog_operation() {
        return log_operation;
    }

    public void setLog_operation(String log_operation) {
        this.log_operation = log_operation;
    }
}
