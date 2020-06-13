package com.julioluis.trainingrest.entities;

import com.julioluis.trainingrest.utils.prototypes.TrainingProptotype;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "session")
public class Session implements TrainingProptotype {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "session_id")
    private Integer id;
    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "session_date")
    private Date startDate;
    @Column(name = "session_place")
    private String location;
    private Integer capacity;
    @JoinColumn(name = "status")
    @ManyToOne
    private Status status;

    @JoinColumn(name = "training_id")
    @ManyToOne
    private Training training;
    @Column(name = "session_name")
    private String sessionName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }



    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }


    @Override
    public TrainingProptotype clone() throws CloneNotSupportedException {
        return (Session) super.clone();
    }
}
