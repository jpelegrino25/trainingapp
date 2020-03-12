package com.julioluis.trainingrest.services;

import com.julioluis.trainingrest.entities.SessionRegister;

import java.util.List;

public interface SessionRegisterService {
    void save(SessionRegister sessionRegister);
    List<SessionRegister> findAttendanceList(Integer proffesor, Integer sessionId);
}
