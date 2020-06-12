package com.julioluis.trainingrest.unit;

import com.julioluis.trainingrest.entities.Session;
import com.julioluis.trainingrest.entities.Training;
import com.julioluis.trainingrest.entities.User;
import com.julioluis.trainingrest.repositories.SessionRepository;
import com.julioluis.trainingrest.services.impl.SessionServiceImpl;
import com.julioluis.trainingrest.utils.BusinessException;
import com.julioluis.trainingrest.utils.SessionHelper;
import com.julioluis.trainingrest.utils.prototypes.ModelType;
import com.julioluis.trainingrest.utils.prototypes.PrototypeFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class SessionServiceUnitTest {

    @Mock
    private SessionRepository sessionRepository;
    @Mock
    private SessionHelper sessionHelper;
    @InjectMocks
    private SessionServiceImpl sessionService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll() {
        List<Session> sessionList= Arrays.asList(new Session());
        when(sessionRepository.findAll()).thenReturn(sessionList);

        List<Session> sessions=sessionService.findAll();
        assertNotNull(sessions);
    }

    @Test
    public void testFinByID() throws CloneNotSupportedException, BusinessException {
        Session session=(Session) PrototypeFactory.trainingProptotype(ModelType.SESSION);
        Optional<Session> sessionOptional=Optional.of(session);
        when(sessionRepository.findById(anyInt())).thenReturn(sessionOptional);

        Session session1=sessionService.findById(1);
        assertNotNull(session1);
    }

    @Test
    public void testSaveSession() throws BusinessException, CloneNotSupportedException {
        Session session=(Session) PrototypeFactory.trainingProptotype(ModelType.SESSION);
        User user=(User) PrototypeFactory.trainingProptotype(ModelType.USER);
        Training training=(Training) PrototypeFactory.trainingProptotype(ModelType.TRAINING);

        session.setUser(user);
        session.setTraining(training);

        when(sessionRepository.save(any(Session.class))).thenReturn(session);
        Session session1=sessionService.saveSession(session);

        assertNotNull(session1);

    }

    @Test
    public void testFindAvailableSessions() throws BusinessException {
        int sessionId = 12;
        Collection<Integer> criteria=Arrays.asList(sessionId);
        when(sessionHelper.getSessionCriteria(anyInt())).thenReturn(criteria);

        Session session = new Session();
        session.setId(sessionId);
        List<Session> sessionList=Arrays.asList(session);

        when(sessionRepository.findAvailableSessionByCriteria(anyCollection())).thenReturn(sessionList);

        List<Session> sessions=sessionService.findAvailableSessions(1);

        assertNotNull(sessions);
        assertEquals(false,sessions.isEmpty());

    }

    @Test
    public void testFindSessionsByInstructor() {
        List<Session> sessionList=Arrays.asList(new Session());
        when(sessionRepository.findSessionsByInstructor(anyInt())).thenReturn(sessionList);

        List<Session> sessions=sessionService.findSessionsByInstructor(1);
        assertNotNull(sessions);
        assertEquals(false,sessions.isEmpty());

    }


}
