package com.julioluis.trainingrest.integration;

import com.julioluis.trainingrest.entities.Training;
import com.julioluis.trainingrest.resources.TrainingResource;
import com.julioluis.trainingrest.utils.UserException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TrainingResourceIntegrationTest {

    @Autowired
    private TrainingResource trainingResource;

    @Test
    public void testFindAll() {
        ResponseEntity<List<Training>> response=trainingResource.findAll();
        assertEquals(200,response.getStatusCode().value());

    }

    @Test
    public void testFindById() {
        int id=1;
        ResponseEntity<Training> response=trainingResource.getOne(id);
        assertEquals(200,response.getStatusCode().value());

    }

    @Test
    public void testFindById_UserException() {
        assertThrows(UserException.class,()-> {
            int id=-1;
            trainingResource.getOne(id);
        });

    }

    @Test
    public void testSaveTraining() {
        Training training=new Training();
        ResponseEntity<Void> response=trainingResource.saveTraining(training);
        assertEquals(201,response.getStatusCode().value());

    }

    @Test
    public void testUpdateTraining() {
        Training training=new Training();
        ResponseEntity<Void> response=trainingResource.updateTraining(training);
        assertEquals(200,response.getStatusCode().value());

    }

    @Test
    public void testDeleteTraining() {
        int id=8;
        ResponseEntity<Void> response=trainingResource.deleteTraining(id);
        assertEquals(200,response.getStatusCode().value());

    }


}
