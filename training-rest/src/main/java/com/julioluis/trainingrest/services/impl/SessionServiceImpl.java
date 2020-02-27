package com.julioluis.trainingrest.services.impl;

import com.julioluis.trainingrest.entities.Session;
import com.julioluis.trainingrest.entities.Status;
import com.julioluis.trainingrest.repositories.SessionRepository;
import com.julioluis.trainingrest.services.SessionService;
import com.julioluis.trainingrest.utils.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    @Override
    public List<Session> findAll() {
        return sessionRepository.findAll();
    }

    @Override
    public Session findById(Integer id) {
        Session session=sessionRepository.findById(id).get();
        return session;
    }

    @Override
    public void saveSession(Session session) {

        sessionRepository.save(session);
    }

    @Override
    public void updateSession(Session session) {
        sessionRepository.save(session);
    }

    @Override
    public void deleteSession(Integer id) {
        Session session=this.findById(id);
        session.setStatus(new Status(StatusEnum.INACTIVE.getStatus()));
        sessionRepository.save(session);
    }
}
