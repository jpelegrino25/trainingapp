package com.julioluis.trainingrest.suit;


import com.julioluis.trainingrest.integration.TrainingResourceIntegrationTests;
import com.julioluis.trainingrest.integration.TrainingServiceIntegrationTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({TrainingServiceIntegrationTests.class,
        TrainingResourceIntegrationTests.class
})
public class TrainingFeatureSuitTest {
}
