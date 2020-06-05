package com.julioluis.trainingrest.suit;

import com.julioluis.trainingrest.integration.UserHelperIntegrationTest;
import com.julioluis.trainingrest.integration.UserRepositoryIntegrationTest;
import com.julioluis.trainingrest.integration.UserResourceIntegrationTest;
import com.julioluis.trainingrest.integration.UserServiceIntegrationTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({UserHelperIntegrationTest.class,
        UserServiceIntegrationTest.class, UserResourceIntegrationTest.class
        })
public class UserFeatureSuit {
}
