package com.julioluis.trainingrest.unit;

import com.julioluis.trainingrest.dto.ResponseDTO;
import com.julioluis.trainingrest.entities.User;
import com.julioluis.trainingrest.repositories.UserRepository;
import com.julioluis.trainingrest.services.UserService;
import com.julioluis.trainingrest.utils.BusinessException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.*;
//import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserServiceUnitTest {

    @Mock
    private UserRepository userRepository;

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
    public void testSaveUserThrowsBusinessException() {
        assertThrows(BusinessException.class,()->{
           userService.saveUser(null);
        });
    }


}
