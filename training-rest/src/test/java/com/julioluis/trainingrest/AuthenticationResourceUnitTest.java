package com.julioluis.trainingrest;

import com.julioluis.trainingrest.entities.User;
import com.julioluis.trainingrest.resources.UserResource;
import com.julioluis.trainingrest.services.UserService;
import com.julioluis.trainingrest.utils.prototypes.ModelType;
import com.julioluis.trainingrest.utils.prototypes.PrototypeFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AuthenticationResourceUnitTest {

    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @InjectMocks
    private UserResource userResource;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() {
        mockMvc= MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @Test
    @WithMockUser("admin")
    public void authenticatedUser() throws Exception {

        User user=(User) PrototypeFactory.trainingProptotype(ModelType.USER);
        user.setUsername("admin");

        when(userService.findUserByUsername(anyString())).thenReturn(user);

        mockMvc.perform(get("/authentications"))
                .andExpect(status().isOk())
                .andReturn();
    }

}
