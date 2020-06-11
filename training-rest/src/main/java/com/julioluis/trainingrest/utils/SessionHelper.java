package com.julioluis.trainingrest.utils;

import com.julioluis.trainingrest.entities.Session;
import com.julioluis.trainingrest.entities.SessionRegister;
import com.julioluis.trainingrest.repositories.SessionRegisterRepository;
import com.julioluis.trainingrest.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Component
public class SessionHelper {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private SessionRegisterRepository sessionRegisterRepository;

    public String generateSessionName(Session session) throws BusinessException {

        if(Objects.isNull(session.getStartDate()))
            throw new BusinessException("Start Date should not be null");

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

    public Session lastSession() {
        Session session=sessionRepository.findLastSession();
        return session;
    }

    public boolean modifyDateRequest(Session session) {

        Calendar calendar=Calendar.getInstance();
        calendar.setTime(session.getStartDate());
        calendar.add(Calendar.DAY_OF_MONTH,1);

        Date newDate = calendar.getTime();
        int compare = newDate.compareTo(session.getStartDate());
        session.setStartDate(newDate);

        return compare >0;

    }

    public Collection<Integer> getSessionCriteria(Integer userId) {
        List<SessionRegister> sessionRegisterList=sessionRegisterRepository
                .findSessionRegisterByUser(userId);

        Collection<Integer> criteriaList=new ArrayList<>();

        for(SessionRegister sessionRegister : sessionRegisterList) {
            criteriaList.add(sessionRegister.getRegisterSessionId().getSession().getId());
        }
        return criteriaList;
    }

}
