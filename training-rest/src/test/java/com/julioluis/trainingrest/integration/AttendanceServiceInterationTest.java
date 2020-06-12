package com.julioluis.trainingrest.integration;

import com.julioluis.trainingrest.entities.Attendance;
import com.julioluis.trainingrest.entities.User;
import com.julioluis.trainingrest.services.AttendanceService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AttendanceServiceInterationTest {

    @Autowired
    private AttendanceService attendanceService;

    @Test
    @Ignore
    public void testSave() {
        Attendance attendance = new Attendance();
        User user=new User();
        user.setId(10);
        attendance.setStudent(user);
        List<Attendance> attendances= Arrays.asList(attendance);
        List<Attendance> attendances1=attendanceService.save(attendances);

        assertNotNull(attendances1);
    }

    @Test
    public void testSave_returnNull() {
        List<Attendance> attendances= Arrays.asList(new Attendance());
        List<Attendance> attendances1=attendanceService.save(attendances);

        assertNull(attendances1);
    }
}
