package com.julioluis.trainingrest.services;

import com.julioluis.trainingrest.entities.Training;

import java.util.List;

public interface TrainingService {

    List<Training> findAll();
    Training findById(Integer id);
    void save(Training training);
    void update(Training training);
    void delete(Integer id);

}
