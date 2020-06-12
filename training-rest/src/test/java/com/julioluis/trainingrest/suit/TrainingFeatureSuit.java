package com.julioluis.trainingrest.suit;


import com.julioluis.trainingrest.integration.TrainingResourceIntegrationTest;
import com.julioluis.trainingrest.integration.TrainingServiceIntegrationTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({TrainingServiceIntegrationTest.class,
        TrainingResourceIntegrationTest.class
})
public class TrainingFeatureSuit {
}
