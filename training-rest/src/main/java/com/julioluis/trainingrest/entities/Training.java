package com.julioluis.trainingrest.entities;

import com.julioluis.trainingrest.utils.prototypes.TrainingProptotype;

import javax.persistence.*;

@Entity
public class Training implements TrainingProptotype {

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

    @Override
    public TrainingProptotype clone() throws CloneNotSupportedException {
        return (Training) super.clone();
    }
}
