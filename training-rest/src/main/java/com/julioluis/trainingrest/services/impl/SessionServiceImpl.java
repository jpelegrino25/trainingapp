package com.julioluis.trainingrest.services.impl;

import com.julioluis.trainingrest.entities.Session;
import com.julioluis.trainingrest.entities.Status;
import com.julioluis.trainingrest.repositories.SessionRepository;
import com.julioluis.trainingrest.services.SessionService;
import com.julioluis.trainingrest.utils.BusinessException;
import com.julioluis.trainingrest.utils.SessionHelper;
import com.julioluis.trainingrest.utils.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private SessionHelper sessionHelper;

    @Override
    public List<Session> findAll() {
        return sessionRepository.findAll();
    }

    @Override
    public Session findById(Integer id) throws BusinessException {
        Optional<Session> optionalSession = sessionRepository.findById(id);
        if (optionalSession.isPresent())
            return optionalSession.get();

        throw new BusinessException("Session not found") ;
    }

    @Override
    public Session saveSession(Session session) throws BusinessException{

        if(Objects.isNull(session.getStatus()))
            throw new BusinessException("Status should not be null");
        if (Objects.isNull(session.getUser()))
            throw new BusinessException("User should not be null");
        if (Objects.isNull(session.getTraining()))
            throw new BusinessException("Training should not be null");

        sessionHelper.modifyDateRequest(session);

        try {
            String sessionName  = sessionHelper.generateSessionName(session);
            session.setSessionName(sessionName);

            Session sessionSaved=sessionRepository.save(session);

            return sessionSaved;
        } catch (BusinessException e) {
            return null;
        }

    }

    @Override
    public Session updateSession(Session session) {
        sessionHelper.modifyDateRequest(session);
        Session sessionUpdate=sessionRepository.save(session);

        return sessionUpdate;
    }

    @Override
    public Session deleteSession(Integer id) throws BusinessException {
        Session session=this.findById(id);
        session.setStatus(new Status(StatusEnum.INACTIVE.getStatus()));
        Session sessionDelete=sessionRepository.save(session);

        return sessionDelete;
    }


    @Override
    public List<Session> findAvailableSessions(Integer userId)throws BusinessException {
        Collection<Integer> criteria=sessionHelper.getSessionCriteria(userId);

        if (criteria.isEmpty())
            throw new BusinessException("This User does not have session assigned");

        List<Session> sessionList=sessionRepository.findAvailableSessionByCriteria(criteria);
        return sessionList;
    }

    @Override
    public List<Session> findSessionsByInstructor(Integer teacher) {
        return sessionRepository.findSessionsByInstructor(teacher);
    }


}
