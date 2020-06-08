package com.julioluis.trainingrest.suit;


import com.julioluis.trainingrest.integration.TrainingResourceIntegration;
import com.julioluis.trainingrest.integration.TrainingServiceIntegration;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({TrainingServiceIntegration.class,
        TrainingResourceIntegration.class
})
public class TrainingFeatureSuit {
}
