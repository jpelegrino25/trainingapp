package com.julioluis.trainingrest;

import com.julioluis.trainingrest.entities.Session;
import com.julioluis.trainingrest.entities.Status;
import com.julioluis.trainingrest.entities.Training;
import com.julioluis.trainingrest.entities.User;
import com.julioluis.trainingrest.services.SessionService;
import com.julioluis.trainingrest.utils.StatusEnum;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class SessionServiceTest {

    @Autowired
    private SessionService sessionService;

    @Test
//    @Ignore
    public void testSaveSession() {
        User user=new User();
        user.setId(5);

        Training training=new Training();
        training.setId(1);



        Session session=new Session();
        session.setCapacity(15);
        session.setStatus(new Status(StatusEnum.ACTIVE.getStatus()));
        session.setLocation("Agora Mall");
        session.setStartDate(new Date());
        session.setUser(user);
        session.setTraining(training);

        sessionService.saveSession(session);

    }
}
