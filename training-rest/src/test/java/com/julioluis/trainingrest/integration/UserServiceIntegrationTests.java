package com.julioluis.trainingrest.integration;

import com.julioluis.trainingrest.dto.ResponseDTO;
import com.julioluis.trainingrest.entities.Rol;
import com.julioluis.trainingrest.entities.Status;
import com.julioluis.trainingrest.entities.User;
import com.julioluis.trainingrest.services.UserService;
import com.julioluis.trainingrest.utils.BusinessException;
import com.julioluis.trainingrest.utils.StatusEnum;
import com.julioluis.trainingrest.utils.prototypes.ModelType;
import com.julioluis.trainingrest.utils.prototypes.PrototypeFactory;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceIntegrationTests {

    @Autowired
    private UserService userService;

    @Test
    public void testSaveUserSuccessful() throws BusinessException, CloneNotSupportedException {

        User user=(User) PrototypeFactory.trainingProptotype(ModelType.USER);
        user.setFirstname("Jennifer");
        user.setPassword("1234");
        Rol rol=new Rol();
        rol.setId(1);
        user.setRol(rol);
        user.setStatus(new Status(StatusEnum.ACTIVE.getStatus()));
        User response = userService.saveUser(user);

        assertNotNull(response);
        assertNotNull(response.getId());

    }


    @Test
    public void testFindUserSuccessful() {
        Integer searchID=1;
        User userFound=userService.findById(searchID);

        assertNotNull(userFound);
        assertNotNull(userFound.getId());
    }

    @Test
    public void testFindUserFailure() {
        Integer searchID=26;
        User userFound=userService.findById(searchID);

        assertNull(userFound);
    }


    @Test
    public void testFindUserByUsernameSuccessful() {
        String username="admin";
        User userFound=userService.findUserByUsername(username);

        assertNotNull(userFound);
        assertNotNull(userFound.getId());

    }


    @Test
    public void testFindUserByUsernameFailure() {
        String username="bamban";
        User userFound=userService.findUserByUsername(username);

        assertNull(userFound);
    }

    @Test
    public void testFindAllUser() {
        List<User> userList=userService.findAllUser();
        assertNotNull(userList);

    }

    @Test
    public void testLoadUserByUsername() {
        String USER="admin";
        UserDetails userDetails=userService.loadUserByUsername(USER);
        assertNotNull(userDetails);
    }

    @Test
    public void findTrainers() {
        List<User> trainers=userService.findTrainers();
        assertNotNull(trainers);

    }

    @Test
    public void deleteUser() throws BusinessException {
        int userId=8;
        User userDeleted=userService.deleteUser(userId);
        int status=userDeleted.getStatus().getId();

        assertEquals(2,status);
    }


    @Test
    public void findAllRoles() {
        List<Rol> roles=userService.findAllRoles();
        assertNotNull(roles);
    }


    


}
