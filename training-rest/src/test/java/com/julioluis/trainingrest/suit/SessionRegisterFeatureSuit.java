package com.julioluis.trainingrest.suit;


import com.julioluis.trainingrest.integration.SessionRegisterResourceIntegrationTest;
import com.julioluis.trainingrest.integration.SessionRegisterServiceIntegrationTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        SessionRegisterServiceIntegrationTest.class,
        SessionRegisterResourceIntegrationTest.class
})
public class SessionRegisterFeatureSuit {
}
