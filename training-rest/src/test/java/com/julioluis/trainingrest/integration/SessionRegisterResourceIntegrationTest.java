package com.julioluis.trainingrest.integration;

import com.julioluis.trainingrest.entities.SessionRegister;
import com.julioluis.trainingrest.entities.User;
import com.julioluis.trainingrest.resources.SessionRegisterResource;
import com.julioluis.trainingrest.services.SessionRegisterService;
import com.julioluis.trainingrest.utils.prototypes.ModelType;
import com.julioluis.trainingrest.utils.prototypes.PrototypeFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SessionRegisterResourceIntegrationTest {

    @Autowired
    private SessionRegisterResource sessionRegisterResource;

    @Test
    public void testSave() throws CloneNotSupportedException {
        SessionRegister sessionRegister=(SessionRegister) PrototypeFactory.trainingProptotype(ModelType.SESSION_REGISTER);
        User user=new User();
        user.setId(3);
        sessionRegister.getRegisterSessionId().setUser(user);


        ResponseEntity<Void> response= sessionRegisterResource.save(sessionRegister);
        assertEquals(201,response.getStatusCode().value());
    }

    @Test
    public void testFindAttendance()  {

        ResponseEntity<List<SessionRegister>> response= sessionRegisterResource.findAttendance(8,6);
        assertEquals(200,response.getStatusCode().value());
    }


}
