package com.julioluis.trainingrest.integration;

import com.julioluis.trainingrest.entities.Session;
import com.julioluis.trainingrest.entities.SessionRegister;
import com.julioluis.trainingrest.services.SessionRegisterService;
import com.julioluis.trainingrest.utils.BusinessException;
import com.julioluis.trainingrest.utils.prototypes.ModelType;
import com.julioluis.trainingrest.utils.prototypes.PrototypeFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class SessionRegisterServiceIntegrationTests {

    @Autowired
    private SessionRegisterService sessionRegisterService;

    @Test
    public void testSave() throws BusinessException, CloneNotSupportedException {
        SessionRegister sessionRegister=(SessionRegister) PrototypeFactory.trainingProptotype(ModelType.SESSION_REGISTER);
        Session session=new Session();
        session.setId(13);
        sessionRegister.getRegisterSessionId().setSession(session);

        SessionRegister sessionRegister1=sessionRegisterService.save(sessionRegister);

        assertNotNull(sessionRegister1);
    }

    @Test
    public void testSave_BusinessException_RegisterSesionId() {
        assertThrows(BusinessException.class,()->{
            SessionRegister sessionRegister=(SessionRegister) PrototypeFactory.trainingProptotype(ModelType.SESSION_REGISTER);
            sessionRegister.setRegisterSessionId(null);

            sessionRegisterService.save(sessionRegister);
        });
    }

    @Test
    public void testSave_BusinessException_Session() {
        assertThrows(BusinessException.class,()->{
            SessionRegister sessionRegister=(SessionRegister) PrototypeFactory.trainingProptotype(ModelType.SESSION_REGISTER);
            sessionRegister.getRegisterSessionId().setSession(null);

            sessionRegisterService.save(sessionRegister);
        });
    }


    @Test
    public void testSave_BusinessException_User() {
        assertThrows(BusinessException.class,()->{
            SessionRegister sessionRegister=(SessionRegister) PrototypeFactory.trainingProptotype(ModelType.SESSION_REGISTER);
            sessionRegister.getRegisterSessionId().setUser(null);

            sessionRegisterService.save(sessionRegister);
        });
    }

}
