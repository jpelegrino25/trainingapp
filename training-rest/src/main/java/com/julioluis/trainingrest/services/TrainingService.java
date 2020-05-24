package com.julioluis.trainingrest.services;

import com.julioluis.trainingrest.entities.Training;

import java.util.List;

public interface TrainingService {

    List<Training> findAll();
    Training findById(Integer id);
    Training save(Training training);
    Training update(Training training);
    Training delete(Integer id);

}
