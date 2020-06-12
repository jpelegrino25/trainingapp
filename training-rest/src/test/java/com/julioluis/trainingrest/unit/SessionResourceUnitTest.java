package com.julioluis.trainingrest.unit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.julioluis.trainingrest.entities.Session;
import com.julioluis.trainingrest.entities.Training;
import com.julioluis.trainingrest.entities.User;
import com.julioluis.trainingrest.resources.SessionResource;
import com.julioluis.trainingrest.services.SessionService;
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

import java.util.Arrays;
import java.util.List;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SessionResourceUnitTest {

    private MockMvc mockMvc;
    @MockBean
    private SessionService sessionService;
    @InjectMocks
    private SessionResource sessionResource;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        mockMvc= MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }


    @WithMockUser
    @Test
    public void testFindAll() throws Exception {
        mockMvc.perform(get("/sessions"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @WithMockUser
    @Test
    public void testGetOne() throws Exception {
        mockMvc.perform(get("/sessions/1"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @WithMockUser
    @Test
    public void findAllAvailableSessions() throws Exception {

        List<Session> sessionList= Arrays.asList(new Session());
        when(sessionService.findAvailableSessions(anyInt())).thenReturn(sessionList);

        mockMvc.perform(get("/sessions/availables/1"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @WithMockUser
    @Test
    public void testFindInstructorSessions() throws Exception {
        List<Session> sessionList= Arrays.asList(new Session());
        when(sessionService.findSessionsByInstructor(anyInt())).thenReturn(sessionList);

        mockMvc.perform(get("/sessions/instructor/1"))
                .andExpect(status().isOk())
                .andReturn();
    }


    @WithMockUser
    @Test
    public void testSave() throws Exception {


        Session session=(Session) PrototypeFactory.trainingProptotype(ModelType.SESSION);

        User user=(User)  PrototypeFactory.trainingProptotype(ModelType.USER);
        user.setId(80);
        Training training=(Training)  PrototypeFactory.trainingProptotype(ModelType.TRAINING);
        training.setId(8);
        session.setUser(user);
        session.setTraining(training);

        when(sessionService.saveSession(any(Session.class))).thenReturn(session);


        String json=objectMapper.writeValueAsString(session);
        session.setId(null);
        mockMvc.perform(post("/sessions").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
    }


    @WithMockUser
    @Test
    public void testUpdate() throws Exception {


        Session session=(Session) PrototypeFactory.trainingProptotype(ModelType.SESSION);
        session.setId(1);
        User user=(User)  PrototypeFactory.trainingProptotype(ModelType.USER);
        user.setId(80);
        Training training=(Training)  PrototypeFactory.trainingProptotype(ModelType.TRAINING);
        training.setId(8);
        session.setUser(user);
        session.setTraining(training);
        session.setLocation("ITLA");


        String json=objectMapper.writeValueAsString(session);

        mockMvc.perform(put("/sessions").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @WithMockUser
    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete("/sessions/1"))
                .andExpect(status().isOk())
                .andReturn();
    }


}
