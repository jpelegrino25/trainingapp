package com.julioluis.trainingrest.services;

import com.julioluis.trainingrest.entities.Session;
import com.julioluis.trainingrest.entities.SessionRegister;
import com.julioluis.trainingrest.utils.BusinessException;

import java.util.List;

public interface SessionService {

    List<Session> findAll();
    Session findById(Integer id) throws BusinessException;
    Session saveSession(Session session)throws BusinessException;
    Session updateSession(Session session);
    Session deleteSession(Integer id) throws BusinessException;
    List<Session> findAvailableSessions(Integer userId) throws BusinessException;
    List<Session> findSessionsByInstructor(Integer teacher);

}
