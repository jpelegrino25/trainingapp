package com.julioluis.trainingrest.suit;

import com.julioluis.trainingrest.integration.UserRepositoryIntegrationTest;
import com.julioluis.trainingrest.integration.UserResourceIntegrationTest;
import com.julioluis.trainingrest.integration.UserServiceIntegrationTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({UserRepositoryIntegrationTest.class,
        UserServiceIntegrationTest.class, UserResourceIntegrationTest.class})
public class AddUserFeatureSuit {
}
