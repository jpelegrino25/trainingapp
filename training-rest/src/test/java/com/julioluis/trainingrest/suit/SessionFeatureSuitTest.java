package com.julioluis.trainingrest.suit;


import com.julioluis.trainingrest.integration.SessionHelperIntegrationTests;
import com.julioluis.trainingrest.integration.SessionResourceIntegrationTests;
import com.julioluis.trainingrest.integration.SessionServiceIntegrationTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        SessionHelperIntegrationTests.class,
        SessionServiceIntegrationTests.class,
        SessionResourceIntegrationTests.class
})
public class SessionFeatureSuitTest {
}
