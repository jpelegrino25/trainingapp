package com.julioluis.trainingrest.integration;

import com.julioluis.trainingrest.entities.Rol;
import com.julioluis.trainingrest.entities.User;
import com.julioluis.trainingrest.resources.UserResource;
import com.julioluis.trainingrest.utils.UserException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserResourceIntegrationTest {
    @Autowired
    private UserResource userResource;

    @Test
    public void testSaveUser() {
        User user=new User();
        user.setPassword("test1234");
        user.setUsername("traningdev");
        user.setFirstname("Administor");
        Rol rol=new Rol();
        rol.setId(1);
        user.setRol(rol);

        ResponseEntity<Void> response=userResource.create(user);

        assertEquals(201,response.getStatusCode().value());

    }


    @Test
    public void testGetAll() {
        ResponseEntity<List<User>> response=userResource.getAll();
        assertEquals(200,response.getStatusCode().value());
    }

    @Test
    public void testGetOne() {
        ResponseEntity<User> response=userResource.getOne(1);
        assertEquals(200,response.getStatusCode().value());
    }

    @Test
    public void testDeleteSuccess() {
        ResponseEntity<Void> response=userResource.delete(8);
        assertEquals(200,response.getStatusCode().value());
    }



}
