package com.julioluis.trainingrest.suit;


import com.julioluis.trainingrest.integration.SessionHelperIntegrationTest;
import com.julioluis.trainingrest.integration.SessionResourceIntegrationTest;
import com.julioluis.trainingrest.integration.SessionServiceIntegrationTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        SessionHelperIntegrationTest.class,
        SessionServiceIntegrationTest.class,
        SessionResourceIntegrationTest.class
})
public class SessionFeatureSuit {
}
