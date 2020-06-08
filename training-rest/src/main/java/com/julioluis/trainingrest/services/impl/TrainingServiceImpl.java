package com.julioluis.trainingrest.services.impl;

import com.julioluis.trainingrest.entities.Status;
import com.julioluis.trainingrest.entities.Training;
import com.julioluis.trainingrest.repositories.TrainingRepository;
import com.julioluis.trainingrest.services.TrainingService;
import com.julioluis.trainingrest.utils.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainingServiceImpl implements TrainingService {

    @Autowired
    private TrainingRepository trainingRepository;


    @Override
    public List<Training> findAll() {
        return trainingRepository.findAll();
    }

    @Override
    public Training findById(Integer id) {
        Optional<Training> trainingOptional = trainingRepository.findById(id);
        if(trainingOptional.isPresent())
        return trainingOptional.get();

        return null;
    }

    @Override
    public Training save(Training training) {
       Training trainingSaved= trainingRepository.save(training);
       return trainingSaved;
    }

    @Override
    public Training update(Training training) {
      Training trainingUpdated=  trainingRepository.save(training);
      return trainingUpdated;
    }

    @Override
    public Training delete(Integer id) {
        Training training=this.findById(id);
        training.setStatus(new Status(StatusEnum.INACTIVE.getStatus()));

       Training trainingDeleted= trainingRepository.save(training);
       return trainingDeleted;
    }
}
