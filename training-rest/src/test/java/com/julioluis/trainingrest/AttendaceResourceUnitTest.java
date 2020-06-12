package com.julioluis.trainingrest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.julioluis.trainingrest.AttendanceDTO;
import com.julioluis.trainingrest.entities.Attendance;
import com.julioluis.trainingrest.entities.User;
import com.julioluis.trainingrest.services.AttendanceService;
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

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AttendaceResourceUnitTest {

    private MockMvc mockMvc;

    @MockBean
    private AttendanceService attendanceService;

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
        Attendance attendance = new Attendance();
        User user=new User();
        user.setId(10);
        attendance.setStudent(user);
        List<Attendance> attendances= Arrays.asList(attendance);
        when(attendanceService.save(anyList())).thenReturn(attendances);

        AttendanceDTO attendanceDTO=new AttendanceDTO();
        attendanceDTO.setAttendances(attendances);

        String json=objectMapper.writeValueAsString(attendanceDTO);

        mockMvc.perform(post("/attendances").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }


}
