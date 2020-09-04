package com.julioluis.trainingrest.services;

import com.julioluis.trainingrest.entities.Status;
import com.julioluis.trainingrest.utils.StatusEnum;

import java.util.List;

public interface StatusService {

    List<Status> findStatus();
}
