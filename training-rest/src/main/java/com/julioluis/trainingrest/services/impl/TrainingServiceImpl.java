package com.julioluis.trainingrest.services.impl;

import com.julioluis.trainingrest.entities.Status;
import com.julioluis.trainingrest.entities.Training;
import com.julioluis.trainingrest.repositories.TrainingRepository;
import com.julioluis.trainingrest.services.TrainingService;
import com.julioluis.trainingrest.utils.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return trainingRepository.findById(id).get();
    }

    @Override
    public void save(Training training) {
        trainingRepository.save(training);
    }

    @Override
    public void update(Training training) {
        trainingRepository.save(training);
    }

    @Override
    public void delete(Integer id) {
        Training training=this.findById(id);
        training.setStatus(new Status(StatusEnum.INACTIVE.getStatus()));

        trainingRepository.save(training);
    }
}
