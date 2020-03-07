package com.julioluis.trainingrest.entities;


import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class RegisterSessionId implements Serializable {

    @JoinColumn(name = "session_id")
    @ManyToOne
    private Session session;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
