package com.julioluis.trainingrest.integration;

import com.julioluis.trainingrest.entities.Training;
import com.julioluis.trainingrest.services.TrainingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TrainingServiceIntegrationTest {

    @Autowired
    private TrainingService trainingService;

    @Test
    public void testFindAll() {
        List<Training> trainings=trainingService.findAll();

        assertNotNull(trainings);
    }

    @Test
    public void testFindById() {
        int id=1;
        Training training=trainingService.findById(id);

        assertNotNull(training);
    }


    @Test
    public void testFindById_Return_Null() {
        int id=-1;
        Training training=trainingService.findById(id);

        assertNull(training);
    }

    @Test
    public void testSave()  {
        Training training=new Training();
        Training trainingSaved=trainingService.save(training);

        assertNotNull(trainingSaved);
        assertNotNull(trainingSaved.getId());
    }

    @Test
    public void testUpdate()  {
        Training training=new Training();
        training.setId(6);
        training.setDescription("Training 1");
        Training trainingSaved=trainingService.update(training);

        assertNotNull(trainingSaved);
        assertEquals("Training 1",trainingSaved.getDescription());
    }


}
