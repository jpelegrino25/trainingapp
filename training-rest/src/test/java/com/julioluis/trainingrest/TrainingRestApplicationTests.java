package com.julioluis.trainingrest;

import com.julioluis.trainingrest.entities.Attendance;
import com.julioluis.trainingrest.entities.Session;
import com.julioluis.trainingrest.entities.User;
import com.julioluis.trainingrest.services.AttendanceService;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class TrainingRestApplicationTests {

    @Autowired
    private AttendanceService attendanceService;

    @Test
    @Ignore
    public void testSaveAttendence() {

        Attendance attendance=new Attendance();
        attendance.setAssisted(true);
        Session session=new Session();
        session.setId(13);
        attendance.setSession(session);

        User user=new User();
        user.setId(8);
        attendance.setStudent(user);

        List<Attendance> attendances=new ArrayList<>();
        attendances.add(attendance);

        attendanceService.save(attendances);

    }

}
