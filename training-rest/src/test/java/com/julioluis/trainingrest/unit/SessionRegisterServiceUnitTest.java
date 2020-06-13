package com.julioluis.trainingrest.unit;

import com.julioluis.trainingrest.entities.RegisterSessionId;
import com.julioluis.trainingrest.entities.Session;
import com.julioluis.trainingrest.entities.SessionRegister;
import com.julioluis.trainingrest.entities.User;
import com.julioluis.trainingrest.repositories.SessionRegisterRepository;
import com.julioluis.trainingrest.services.SessionRegisterService;
import com.julioluis.trainingrest.services.impl.SessionRegisterServiceImpl;
import com.julioluis.trainingrest.utils.BusinessException;
import com.julioluis.trainingrest.utils.prototypes.ModelType;
import com.julioluis.trainingrest.utils.prototypes.PrototypeFactory;
import org.junit.Before;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class SessionRegisterServiceUnitTest {

    @Mock
    private SessionRegisterRepository sessionRegisterRepository;
    @InjectMocks
    private SessionRegisterServiceImpl sessionRegisterService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSave() throws BusinessException, CloneNotSupportedException {

        SessionRegister sessionRegister=(SessionRegister) PrototypeFactory.trainingProptotype(ModelType.SESSION_REGISTER);
        when(sessionRegisterRepository.save(any(SessionRegister.class))).thenReturn(sessionRegister);

        SessionRegister sessionRegister1=sessionRegisterService.save(sessionRegister);
        assertNotNull(sessionRegister1);
    }

    @Test
    public void testFindAttendanceList() {
        List<SessionRegister> sessionRegisterList= Arrays.asList(new SessionRegister());
        when(sessionRegisterRepository.findStudentsByProfessorAndSession(anyInt(),anyInt())).thenReturn(sessionRegisterList);

        List<SessionRegister> sessionRegisters=sessionRegisterService.findAttendanceList(1,2);
        assertNotNull(sessionRegisters);
    }



}
