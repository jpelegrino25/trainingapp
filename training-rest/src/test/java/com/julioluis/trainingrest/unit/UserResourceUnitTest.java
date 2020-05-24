package com.julioluis.trainingrest.unit;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.julioluis.trainingrest.entities.User;
import com.julioluis.trainingrest.resources.UserResource;
import com.julioluis.trainingrest.services.UserService;
import org.hamcrest.Matchers;
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

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@RunWith(SpringRunner.class)
@SpringBootTest
public class UserResourceUnitTest {


    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @InjectMocks
    private UserResource userResource;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        mockMvc= MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @WithMockUser("/admin")
    @Test
    public void testCreate() throws Exception {

        User user=new User();
        user.setPassword("1234");

        when(userService.saveUser(any(User.class))).thenReturn(user);

        User newUser=new User();
        newUser.setPassword("8990");
        newUser.setFirstname("Pedro");
        newUser.setLastname("Martinez");

        String jsonObject=objectMapper.writeValueAsString(newUser);

        mockMvc.perform(post("/users").content(jsonObject).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();



    }





}
