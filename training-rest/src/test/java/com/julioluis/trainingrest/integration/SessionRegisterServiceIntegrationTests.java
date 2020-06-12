package com.julioluis.trainingrest.integration;

import com.julioluis.trainingrest.entities.RegisterSessionId;
import com.julioluis.trainingrest.entities.Session;
import com.julioluis.trainingrest.entities.SessionRegister;
import com.julioluis.trainingrest.entities.User;
import com.julioluis.trainingrest.services.SessionRegisterService;
import com.julioluis.trainingrest.utils.BusinessException;
import com.julioluis.trainingrest.utils.prototypes.ModelType;
import com.julioluis.trainingrest.utils.prototypes.PrototypeFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
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

}
