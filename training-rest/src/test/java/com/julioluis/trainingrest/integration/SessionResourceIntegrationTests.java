package com.julioluis.trainingrest.integration;

import com.julioluis.trainingrest.entities.Session;
import com.julioluis.trainingrest.entities.Training;
import com.julioluis.trainingrest.entities.User;
import com.julioluis.trainingrest.resources.SessionResource;
import com.julioluis.trainingrest.utils.UserException;
import com.julioluis.trainingrest.utils.prototypes.ModelType;
import com.julioluis.trainingrest.utils.prototypes.PrototypeFactory;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SessionResourceIntegrationTests {

    @Autowired
    private SessionResource sessionResource;

    @Test
    public void testFindAll() {
        ResponseEntity<List<Session>> response=sessionResource.findAll();
        assertEquals(200,response.getStatusCode().value());

    }

    @Test
    public void testFinOne() {
        ResponseEntity<Session> response=sessionResource.getOne(6);
        assertEquals(200,response.getStatusCode().value());

    }

    @Test
    public void testFindAllAvailableSessions() {
        ResponseEntity<List<Session>> response=sessionResource.findAllAvailableSessions(8);
        assertEquals(200,response.getStatusCode().value());
    }

    @Test
    public void findInstructorSessions() {
        ResponseEntity<List<Session>> response=sessionResource.findInstructorSessions(6);
        assertEquals(200,response.getStatusCode().value());
    }

    @Test
    public void testSave() throws CloneNotSupportedException {
        Session session=(Session) PrototypeFactory.trainingProptotype(ModelType.SESSION);
        User user=(User)  PrototypeFactory.trainingProptotype(ModelType.USER);
        user.setId(80);
        Training training=(Training)  PrototypeFactory.trainingProptotype(ModelType.TRAINING);
        training.setId(8);
        session.setUser(user);
        session.setTraining(training);

        ResponseEntity<Void> response=sessionResource.save(session);
        assertEquals(201,response.getStatusCode().value());
    }

    @Test
    public void testUpdate() throws CloneNotSupportedException {
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

        ResponseEntity<Void> response=sessionResource.update(session);
        assertEquals(200,response.getStatusCode().value());
    }

    @Test
    public void testDelete() {

        ResponseEntity<Void> response=sessionResource.delete(18);
        assertEquals(200,response.getStatusCode().value());
    }


}
