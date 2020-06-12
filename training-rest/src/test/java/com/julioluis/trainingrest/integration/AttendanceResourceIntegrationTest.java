package com.julioluis.trainingrest.integration;

import com.julioluis.trainingrest.AttendanceDTO;
import com.julioluis.trainingrest.entities.Attendance;
import com.julioluis.trainingrest.entities.User;
import com.julioluis.trainingrest.resources.AttendanceResource;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AttendanceResourceIntegrationTest {

    @Autowired
    private AttendanceResource attendanceResource;

    @Test
    @Ignore
    public void testSave() {
        Attendance attendance = new Attendance();
        User user=new User();
        user.setId(10);
        attendance.setStudent(user);
        List<Attendance> attendances= Arrays.asList(attendance);
        AttendanceDTO attendanceDTO=new AttendanceDTO();
        attendanceDTO.setAttendances(attendances);

        ResponseEntity<Void> response=attendanceResource.save(attendanceDTO);
        assertEquals(200,response.getStatusCode().value());
    }


}
