package com.julioluis.trainingrest.services.impl;

import com.julioluis.trainingrest.entities.Session;
import com.julioluis.trainingrest.entities.SessionRegister;
import com.julioluis.trainingrest.entities.Status;
import com.julioluis.trainingrest.repositories.SessionRegisterRepository;
import com.julioluis.trainingrest.repositories.SessionRepository;
import com.julioluis.trainingrest.repositories.UserRepository;
import com.julioluis.trainingrest.services.SessionService;
import com.julioluis.trainingrest.utils.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionRegisterRepository sessionRegisterRepository;

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
        this.modifyDateRequest(session);
        session.setSessionName(this.generateSessionName(session));
        sessionRepository.save(session);
    }

    @Override
    public void updateSession(Session session) {
        this.modifyDateRequest(session);
        sessionRepository.save(session);
    }

    @Override
    public void deleteSession(Integer id) {
        Session session=this.findById(id);
        session.setStatus(new Status(StatusEnum.INACTIVE.getStatus()));
        sessionRepository.save(session);
    }

    @Override
    public Session lastSession() {
        Session session=sessionRepository.findLastSession();
        return session;
    }

    @Override
    public List<Session> findAvailableSessions(Integer userId) {
        Collection<Integer> criteria=this.getRegisteredSessionsByUser(userId);
        List<Session> sessionList=sessionRepository.findAvailableSessionByCriteria(criteria);
        return sessionList;
    }

    @Override
    public List<Session> findSessionsByInstructor(Integer teacher) {
        return sessionRepository.findSessionsByInstructor(teacher);
    }


    private Collection<Integer> getRegisteredSessionsByUser(Integer userId) {
        List<SessionRegister> sessionRegisterList=sessionRegisterRepository
                .findSessionRegisterByUser(userId);

        Collection<Integer> criteriaList=new ArrayList<>();

       StringBuilder sb=new StringBuilder();
        for(SessionRegister sessionRegister : sessionRegisterList) {
           criteriaList.add(sessionRegister.getRegisterSessionId().getSession().getId());
        }
        return criteriaList;
    }

    private String generateSessionName(Session session) {

        Session lastSession=this.lastSession();

        int next= Objects.isNull(lastSession)?0:
                Integer.valueOf(lastSession.getSessionName().
                        substring(lastSession.getSessionName().length()-1));

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        LocalDate localDate=LocalDate.parse(simpleDateFormat.format(session.getStartDate()));
        next++;

        String sessionGenerated=String.valueOf(localDate.getYear())+String.valueOf(localDate.getMonthValue())+"-"+next;


        return sessionGenerated;
    }

    private void modifyDateRequest(Session session) {
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(session.getStartDate());
        calendar.add(Calendar.DAY_OF_MONTH,1);

        session.setStartDate(calendar.getTime());
    }
}
