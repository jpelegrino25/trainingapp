package com.julioluis.trainingrest.entities;

import javax.persistence.*;

@Table(name = "attendance")
@Entity
public class Attendance {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JoinColumn(name = "student")
    @ManyToOne
    private User student;
    @JoinColumn(name = "session_id")
    @ManyToOne
    private Session session;
    private boolean assisted;
    @JoinColumn(name = "status")
    @ManyToOne
    private Status status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public boolean isAssisted() {
        return assisted;
    }

    public void setAssisted(boolean assisted) {
        this.assisted = assisted;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
