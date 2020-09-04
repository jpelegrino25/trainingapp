package com.julioluis.trainingrest.integration;

import com.julioluis.trainingrest.entities.Status;
import com.julioluis.trainingrest.services.StatusService;
import com.julioluis.trainingrest.utils.StatusEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
public class StatusServiceTestIntegration {

    @Autowired
    private StatusService statusService;

    @Test
    public void testFindStatus() {
        List<Status> statuses=statusService.findStatus();
        assertNotNull(statuses);
    }
}
