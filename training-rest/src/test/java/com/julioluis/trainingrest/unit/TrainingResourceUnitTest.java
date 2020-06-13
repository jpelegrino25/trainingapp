//package com.julioluis.trainingrest.unit;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.julioluis.trainingrest.entities.Training;
//import com.julioluis.trainingrest.resources.TrainingResource;
//import com.julioluis.trainingrest.services.TrainingService;
//import org.junit.Before;
//
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import java.util.Arrays;
//import java.util.List;
//
//
//import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class TrainingResourceUnitTest {
//
//    private MockMvc mockMvc;
//    @MockBean
//    private TrainingService trainingService;
//
//    @InjectMocks
//    private TrainingResource trainingResource;
//
//    @Autowired
//    private WebApplicationContext context;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Before
//    public void setUp() {
//        mockMvc= MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
//    }
//
//    @WithMockUser("admin")
//    @Test
//    public void testFindAll() throws Exception {
//        List<Training> trainingList= Arrays.asList(new Training());
//        when(trainingService.findAll()).thenReturn(trainingList);
//
//        mockMvc.perform(get("/trainings"))
//                .andExpect(status().isOk())
//                .andReturn();
//    }
//
//    @WithMockUser("admin")
//    @Test
//    public void testFindId() throws Exception {
//
//        Training training=new Training();
//        when(trainingService.findById(anyInt())).thenReturn(training);
//
//        mockMvc.perform(get("/trainings/1"))
//                .andExpect(status().isOk())
//                .andReturn();
//    }
//
//    @WithMockUser("admin")
//    @Test
//    public void testSaveTraining() throws Exception {
//
//        Training training=new Training();
//        when(trainingService.save(any(Training.class))).thenReturn(training);
//
//        Training request=new Training();
//        String json=objectMapper.writeValueAsString(request);
//
//        mockMvc.perform(post("/trainings").content(json).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated())
//                .andReturn();
//    }
//
//    @WithMockUser("admin")
//    @Test
//    public void testUpdateTraining() throws Exception {
//
//        Training training=new Training();
//        when(trainingService.update(any(Training.class))).thenReturn(training);
//
//        Training request=new Training();
//        String json=objectMapper.writeValueAsString(request);
//
//        mockMvc.perform(put("/trainings").content(json).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andReturn();
//    }
//
//    @WithMockUser("admin")
//    @Test
//    public void testDeleteTraining() throws Exception {
//
//        Training training=new Training();
//        when(trainingService.delete(anyInt())).thenReturn(training);
//
//        mockMvc.perform(delete("/trainings/1"))
//                .andExpect(status().isOk())
//                .andReturn();
//    }
//
//
//}
