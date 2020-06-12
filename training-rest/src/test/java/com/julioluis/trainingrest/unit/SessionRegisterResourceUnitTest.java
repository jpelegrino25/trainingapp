package com.julioluis.trainingrest.unit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.julioluis.trainingrest.entities.RegisterSessionId;
import com.julioluis.trainingrest.entities.Session;
import com.julioluis.trainingrest.entities.SessionRegister;
import com.julioluis.trainingrest.entities.User;
import com.julioluis.trainingrest.services.SessionRegisterService;
import com.julioluis.trainingrest.utils.prototypes.ModelType;
import com.julioluis.trainingrest.utils.prototypes.PrototypeFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SessionRegisterResourceUnitTest {

    private MockMvc mockMvc;

    @MockBean
    private SessionRegisterService sessionRegisterService;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        mockMvc= MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }


    @Test
    @WithMockUser
    public void testSave() throws Exception {

        SessionRegister sessionRegister=(SessionRegister) PrototypeFactory.trainingProptotype(ModelType.SESSION_REGISTER);

        when(sessionRegisterService.save(any(SessionRegister.class))).thenReturn(sessionRegister);

        String json=objectMapper.writeValueAsString(sessionRegister);

        mockMvc.perform(post("/registers").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

    }


    @Test
    @WithMockUser
    public void testFindAttendanceList() throws Exception {

        List<SessionRegister> sessionRegisters= Arrays.asList(new SessionRegister());

        when(sessionRegisterService.findAttendanceList(anyInt(),anyInt())).thenReturn(sessionRegisters);

        mockMvc.perform(get("/registers/attendance/1/1"))
                .andExpect(status().isOk())
                .andReturn();

    }


}
