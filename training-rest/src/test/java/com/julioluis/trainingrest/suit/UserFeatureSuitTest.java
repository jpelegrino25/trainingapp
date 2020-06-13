package com.julioluis.trainingrest.suit;

import com.julioluis.trainingrest.integration.UserHelperIntegrationTests;
import com.julioluis.trainingrest.integration.UserResourceIntegrationTests;
import com.julioluis.trainingrest.integration.UserServiceIntegrationTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({UserHelperIntegrationTests.class,
        UserServiceIntegrationTests.class, UserResourceIntegrationTests.class
        })
public class UserFeatureSuitTest {
}
