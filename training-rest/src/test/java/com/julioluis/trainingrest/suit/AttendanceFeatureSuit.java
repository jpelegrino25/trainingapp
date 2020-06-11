package com.julioluis.trainingrest.suit;


import com.julioluis.trainingrest.integration.AttendanceResourceIntegrationTest;
import com.julioluis.trainingrest.integration.AttendanceServiceInterationTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AttendanceServiceInterationTest.class,
        AttendanceResourceIntegrationTest.class
})
public class AttendanceFeatureSuit {
}
