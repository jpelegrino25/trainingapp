package com.julioluis.trainingrest.integration;

import com.julioluis.trainingrest.dto.ResponseDTO;
import com.julioluis.trainingrest.entities.User;
import com.julioluis.trainingrest.services.UserService;
import com.julioluis.trainingrest.utils.BusinessException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @Test
    public void testSaveUserSuccessful() throws BusinessException {

        User user = new User();
        user.setFirstname("Jennifer");
        user.setPassword("1234");
        User response = userService.saveUser(user);

        assertNotNull(response);
        assertNotNull(response.getId());

    }


}
