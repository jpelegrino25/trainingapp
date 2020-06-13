package com.julioluis.trainingrest.unit;

import com.julioluis.trainingrest.dto.ResponseDTO;
import com.julioluis.trainingrest.entities.Status;
import com.julioluis.trainingrest.entities.User;
import com.julioluis.trainingrest.repositories.UserRepository;
import com.julioluis.trainingrest.services.UserService;
import com.julioluis.trainingrest.utils.BusinessException;
import com.julioluis.trainingrest.utils.StatusEnum;
import com.julioluis.trainingrest.utils.UserHelper;
import com.julioluis.trainingrest.utils.prototypes.ModelType;
import com.julioluis.trainingrest.utils.prototypes.PrototypeFactory;
import org.junit.Before;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserServiceUnitTest {

    @Mock
    private UserRepository userRepository;

    @Autowired
    @Mock
    private UserHelper userHelper;

    @InjectMocks
    private UserService userService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveUser() throws BusinessException {
        User user=new User();
        user.setPassword("1234");

        when(userRepository.save(any(User.class))).thenReturn(user);
        User userSaved=userService.saveUser(user);
        verify(userRepository).save(user);

        assertNotNull(userSaved);
        assertEquals(user.getPassword(),userSaved.getPassword());

    }

    @Test
    public void testFindUserSuccessful() {
        Integer searchID=1;
        User user=new User();
        user.setId(searchID);

        Optional<User> userOptional=Optional.of(user);

        when(userRepository.findById(anyInt())).thenReturn(userOptional);

        User userFound=userService.findById(searchID);

        assertNotNull(userFound);
        assertNotNull(userFound.getId());
    }

    @Test
    public void testFindUserByUsername() {
        String username="username";
        User user=new User();
        user.setId(1);
        Optional<User> userOptional=Optional.of(user);

        when(userRepository.findByUsername(anyString())).thenReturn(userOptional);

        User userFound=userService.findUserByUsername(username);
        assertNotNull(userFound);
        assertNotNull(userFound.getId());
    }


    @Test
    public void testFindAllUser_NotFound() {
        List<User> userList= Arrays.asList();
        when(userRepository.getAllActiveUsers()).thenReturn(userList);

        List<User> users=userService.findAllUser();

        assertNotNull(users);
        assertEquals(true,users.isEmpty());

    }

    @Test
    public void testFindTrainers() throws CloneNotSupportedException {
        User user=(User) PrototypeFactory.trainingProptotype(ModelType.USER);
        user.getRol().setId(2);

        List<User>userList=Arrays.asList(user);
        when(userRepository.getAllInstructors()).thenReturn(userList);

        List<User> trainers=userService.findTrainers();
        assertNotNull(trainers);

    }


}
