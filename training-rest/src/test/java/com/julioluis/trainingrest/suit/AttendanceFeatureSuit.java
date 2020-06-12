package com.julioluis.trainingrest.suit;


import com.julioluis.trainingrest.integration.AttendanceResourceIntegrationTests;
import com.julioluis.trainingrest.integration.AttendanceServiceInterationTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AttendanceServiceInterationTests.class,
        AttendanceResourceIntegrationTests.class
})
public class AttendanceFeatureSuit {
}
