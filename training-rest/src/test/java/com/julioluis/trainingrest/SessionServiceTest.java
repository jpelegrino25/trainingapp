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

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
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

    @Test
    @Ignore
    public void testFindLastSession() {
//        Session session=sessionService.lastSession();
//        System.out.println(session);

        String sessionName="202002-1";
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");


        Date date=new Date();
        LocalDate localDate=LocalDate.parse(simpleDateFormat.format(date));
        int next=Integer.valueOf(sessionName.substring(sessionName.length()-1));
        next++;
        String generate=String.valueOf(localDate.getYear())+String.valueOf(localDate.getMonthValue())+"-"+next;
        System.out.println(generate);

    }
}
