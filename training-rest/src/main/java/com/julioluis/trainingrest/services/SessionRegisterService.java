package com.julioluis.trainingrest.services;

import com.julioluis.trainingrest.entities.SessionRegister;
import com.julioluis.trainingrest.utils.BusinessException;

import java.util.List;

public interface SessionRegisterService {
    SessionRegister save(SessionRegister sessionRegister) throws BusinessException;
    List<SessionRegister> findAttendanceList(Integer proffesor, Integer sessionId);
}
