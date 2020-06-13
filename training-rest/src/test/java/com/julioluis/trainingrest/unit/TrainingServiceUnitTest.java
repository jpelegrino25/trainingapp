package com.julioluis.trainingrest.unit;

import com.julioluis.trainingrest.entities.Training;
import com.julioluis.trainingrest.repositories.TrainingRepository;
import com.julioluis.trainingrest.services.TrainingService;
import com.julioluis.trainingrest.services.impl.TrainingServiceImpl;
import com.julioluis.trainingrest.utils.prototypes.ModelType;
import com.julioluis.trainingrest.utils.prototypes.PrototypeFactory;
import org.junit.Before;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TrainingServiceUnitTest {

    @Mock
    private TrainingRepository trainingRepository;

    @InjectMocks
    private TrainingServiceImpl trainingService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll() {
        List<Training> trainingList= Arrays.asList(new Training());
        when(trainingRepository.findAll()).thenReturn(trainingList);

        List<Training> trainings=trainingService.findAll();
        assertNotNull(trainings);
        assertEquals(false,trainings.isEmpty());
    }

    @Test
    public void testFindById() throws CloneNotSupportedException {
        Training training=(Training) PrototypeFactory.trainingProptotype(ModelType.TRAINING);
        Optional<Training> trainingOptional=Optional.of(training);
        when(trainingRepository.findById(anyInt())).thenReturn(trainingOptional);

        Training trainingFound=trainingService.findById(1);
        assertNotNull(trainingFound);

    }


    @Test
    public void testSave()  {
        Training training=new Training();
        when(trainingRepository.save(any(Training.class))).thenReturn(training);

        Training trainingSaved=trainingService.save(training);

        verify(trainingRepository).save(any(Training.class));

        assertNotNull(trainingSaved);

    }


}
