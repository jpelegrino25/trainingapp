//package com.julioluis.trainingrest.integration;
//
//import com.julioluis.trainingrest.entities.Attendance;
//import com.julioluis.trainingrest.utils.AttendanceHelper;
//import com.julioluis.trainingrest.utils.BusinessException;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.Assert.*;
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class AttendanceHelperIntegrationTest {
//
//    @Autowired
//    private AttendanceHelper attendanceHelper;
//
//    @Test
//    public void testValidateStudent_BusinessException_emptyAttendance() {
//        assertThrows(BusinessException.class,()->{
//            List<Attendance> attendances= Arrays.asList();
//            attendanceHelper.validateStudent(attendances);
//        });
//    }
//
//    @Test
//    public void testValidateStudent_BusinessException_studentNull() {
//        assertThrows(BusinessException.class,()->{
//            List<Attendance> attendances= Arrays.asList(new Attendance());
//            attendanceHelper.validateStudent(attendances);
//        });
//    }
//
//}
