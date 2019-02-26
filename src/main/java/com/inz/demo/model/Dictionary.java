package com.inz.demo.model;

import javax.persistence.*;

@Table(name = "dictionary")
@Entity
public class Dictionary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dict_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dict_class_id")
    private Class dict_class_id;

    @Column(name = "dict_name")
    private String dict_name;

    public Dictionary(Class dict_class_id, String dict_name) {
        this.dict_class_id = dict_class_id;
        this.dict_name = dict_name;
    }

    public Dictionary() {
    }

    @Override
    public String toString() {
        return "Dictionary{" +
                "dict_id=" + dict_id +
                ", dict_class_id=" + dict_class_id +
                ", dict_name='" + dict_name + '\'' +
                '}';
    }

    public Integer getDict_id() {
        return dict_id;
    }

    public void setDict_id(Integer dict_id) {
        this.dict_id = dict_id;
    }

    public Class getDict_class_id() {
        return dict_class_id;
    }

    public void setDict_class_id(Class dict_class_id) {
        this.dict_class_id = dict_class_id;
    }

    public String getDict_name() {
        return dict_name;
    }

    public void setDict_name(String dict_name) {
        this.dict_name = dict_name;
    }
}
