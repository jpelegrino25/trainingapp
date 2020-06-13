package com.julioluis.trainingrest.unit;

import com.julioluis.trainingrest.entities.RegisterSessionId;
import com.julioluis.trainingrest.entities.Session;
import com.julioluis.trainingrest.entities.SessionRegister;
import com.julioluis.trainingrest.repositories.SessionRegisterRepository;
import com.julioluis.trainingrest.repositories.SessionRepository;
import com.julioluis.trainingrest.utils.SessionHelper;
import org.junit.Before;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@SpringBootTest
public class SessionHelperUnitTest {

    @Mock
    private SessionRepository sessionRepository;
    @Mock
    private SessionRegisterRepository sessionRegisterRepository;
    @InjectMocks
    private SessionHelper sessionHelper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLastSession() {
        Session session=new Session();
        when(sessionRepository.findLastSession()).thenReturn(session);

        Session session1=sessionHelper.lastSession();
        assertNotNull(session1);

    }

    @Test
    public void testGetSessionCriteria() {

        RegisterSessionId registerSessionId=new RegisterSessionId();
        Session session = new Session();
        session.setId(12);
        registerSessionId.setSession(session);
        SessionRegister sessionRegister=new SessionRegister();
        sessionRegister.setRegisterSessionId(registerSessionId);

        List<SessionRegister> sessionRegisters=Arrays.asList(sessionRegister);

        when(sessionRegisterRepository.findSessionRegisterByUser(anyInt())).thenReturn(sessionRegisters);

        Collection<Integer> sessionCriteria=sessionHelper.getSessionCriteria(1);
        assertNotNull(sessionCriteria);
        assertEquals(false,sessionCriteria.isEmpty());
    }


}
