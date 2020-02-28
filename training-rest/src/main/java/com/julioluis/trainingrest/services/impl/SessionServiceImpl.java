package com.julioluis.trainingrest.services.impl;

import com.julioluis.trainingrest.entities.Session;
import com.julioluis.trainingrest.entities.Status;
import com.julioluis.trainingrest.repositories.SessionRepository;
import com.julioluis.trainingrest.repositories.UserRepository;
import com.julioluis.trainingrest.services.SessionService;
import com.julioluis.trainingrest.utils.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private UserRepository userRepository;

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
        session.setSessionName(this.generateSessionName(session));
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

    @Override
    public Session lastSession() {
        Session session=sessionRepository.findLastSession();
        return session;
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
}
