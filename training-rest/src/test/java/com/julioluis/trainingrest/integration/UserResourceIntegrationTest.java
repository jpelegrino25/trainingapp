package com.julioluis.trainingrest.integration;

import com.julioluis.trainingrest.entities.Rol;
import com.julioluis.trainingrest.entities.User;
import com.julioluis.trainingrest.resources.UserResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserResourceIntegrationTest {
    @Autowired
    private UserResource userResource;

    @Test
    public void testSaveUser() {
        User user=new User();
        user.setPassword("admin");
        user.setUsername("admin");
        user.setFirstname("Administor");
        Rol rol=new Rol();
        rol.setId(1);
        user.setRol(rol);

        ResponseEntity<User> response=userResource.create(user);

        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getId());


    }
}
