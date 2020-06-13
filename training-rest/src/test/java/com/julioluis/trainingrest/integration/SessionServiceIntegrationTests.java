package com.julioluis.trainingrest.integration;

import com.julioluis.trainingrest.entities.Session;
import com.julioluis.trainingrest.entities.Training;
import com.julioluis.trainingrest.entities.User;
import com.julioluis.trainingrest.services.SessionService;
import com.julioluis.trainingrest.utils.BusinessException;
import com.julioluis.trainingrest.utils.prototypes.ModelType;
import com.julioluis.trainingrest.utils.prototypes.PrototypeFactory;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class SessionServiceIntegrationTests {

    @Autowired
    private SessionService sessionService;

    @Test
    public void testFindAll() {
        List<Session> sessionList=sessionService.findAll();
        assertNotNull(sessionList);
    }

    @Test
    public void testFindById() throws BusinessException {
        Session session=sessionService.findById(6);
        assertNotNull(session);
    }



    @Test
    public void testSaveSession() throws CloneNotSupportedException, BusinessException {
        Session session=(Session) PrototypeFactory.trainingProptotype(ModelType.SESSION);
        User user=(User)  PrototypeFactory.trainingProptotype(ModelType.USER);
        user.setId(80);
        Training training=(Training)  PrototypeFactory.trainingProptotype(ModelType.TRAINING);
        training.setId(8);
        session.setUser(user);
        session.setTraining(training);

        Session session1=sessionService.saveSession(session);
        assertNotNull(session1);
    }

    @Test
    public void testUpdateSession() throws CloneNotSupportedException {
        Session session=(Session) PrototypeFactory.trainingProptotype(ModelType.SESSION);
        session.setId(17);
        User user=(User)  PrototypeFactory.trainingProptotype(ModelType.USER);
        user.setId(80);
        Training training=(Training)  PrototypeFactory.trainingProptotype(ModelType.TRAINING);
        training.setId(8);
        session.setUser(user);
        session.setTraining(training);
        String location = "Instituto Tecnologico de las Americas";
        session.setLocation(location);

        Session session1=sessionService.updateSession(session);
        assertEquals(location,session1.getLocation());

    }



    @Test
    public void testFindAvailableSessions() throws BusinessException {
        List<Session> sessionList=sessionService.findAvailableSessions(8);
        assertNotNull(sessionList);
        assertEquals(false,sessionList.isEmpty());
    }

    @Test
    public void testFindSessionsByInstructor() {
        List<Session> sessionList=sessionService.findSessionsByInstructor(6);
        assertNotNull(sessionList);
        assertEquals(false,sessionList.isEmpty());
    }

    @Test
    public void testSaveSession_throws_businessException_status()  {
        assertThrows(BusinessException.class,()->{
            Session session=(Session) PrototypeFactory.trainingProptotype(ModelType.SESSION);
            session.setStatus(null);
            sessionService.saveSession(session);
        });
    }

    @Test
    public void testSaveSession_throws_businessException_User()  {
        assertThrows(BusinessException.class,()->{
            Session session=(Session) PrototypeFactory.trainingProptotype(ModelType.SESSION);
            sessionService.saveSession(session);
        });
    }

    @Test
    public void testSaveSession_throws_businessException_Training()  {
        assertThrows(BusinessException.class,()->{
            Session session=(Session) PrototypeFactory.trainingProptotype(ModelType.SESSION);
            User user=(User) PrototypeFactory.trainingProptotype(ModelType.USER);
            session.setUser(user);
            sessionService.saveSession(session);
        });
    }

}
