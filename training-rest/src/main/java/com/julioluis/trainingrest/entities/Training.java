package com.julioluis.trainingrest.entities;

import javax.persistence.*;

@Entity
public class Training {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    @JoinColumn(name = "status")
    @ManyToOne
    private Status status;

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
