package com.julioluis.trainingrest.integration;


import com.julioluis.trainingrest.entities.Session;
import com.julioluis.trainingrest.utils.BusinessException;
import com.julioluis.trainingrest.utils.SessionHelper;
import io.swagger.models.auth.In;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
public class SessionHelperIntegrationTests {

    @Autowired
    private SessionHelper sessionHelper;

    @Test
    public void testLastSession() {
        Session session=sessionHelper.lastSession();
        assertNotNull(session);
    }

    @Test
    public void testGenerateSessionName() throws BusinessException {
        Session session=new Session();
        session.setStartDate(new Date());

        String generatedSessionName=sessionHelper.generateSessionName(session);

        assertNotNull(generatedSessionName);
        assertEquals(false,generatedSessionName.isEmpty());

    }

    @Test
    public void testModifyDateRequest() {
        Session session=new Session();
        session.setStartDate(new Date());

        boolean result=sessionHelper.modifyDateRequest(session);

        assertEquals(true,result);
    }

    @Test
    public void testGetSessionCriteria() {
        int userId = 8;
        Collection<Integer> criteria=sessionHelper.getSessionCriteria(userId);

        assertNotNull(criteria);
        assertEquals(false,criteria.isEmpty());
    }
}
