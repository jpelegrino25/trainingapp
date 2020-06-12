package com.julioluis.trainingrest.suit;


import com.julioluis.trainingrest.integration.SessionRegisterResourceIntegrationTests;
import com.julioluis.trainingrest.integration.SessionRegisterServiceIntegrationTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        SessionRegisterServiceIntegrationTests.class,
        SessionRegisterResourceIntegrationTests.class
})
public class SessionRegisterFeatureSuit {
}
