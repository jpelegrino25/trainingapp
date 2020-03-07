package com.julioluis.trainingrest.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "session_register")
public class SessionRegister implements Serializable {

    @EmbeddedId
    private RegisterSessionId registerSessionId;
    @JoinColumn(name = "status")
    @ManyToOne
    private Status status;

    public RegisterSessionId getRegisterSessionId() {
        return registerSessionId;
    }

    public void setRegisterSessionId(RegisterSessionId registerSessionId) {
        this.registerSessionId = registerSessionId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
