package com.julioluis.trainingrest.entities;

import javax.persistence.*;

@Entity
@Table(name = "status")
public class Status {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;

    public Status(Integer id) {
        this.id=id;
    }

    public Status(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Status() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
