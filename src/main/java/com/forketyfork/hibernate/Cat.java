package com.forketyfork.hibernate;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Cat implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void myCustomMethodToChangeData() {
        this.name = "Simba";
    }
}
