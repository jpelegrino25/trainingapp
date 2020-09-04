package com.julioluis.trainingrest.services.impl;

import com.julioluis.trainingrest.entities.Status;
import com.julioluis.trainingrest.services.StatusService;
import com.julioluis.trainingrest.utils.StatusEnum;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class StatusServiceImpl implements StatusService {

    @Override
    public List<Status> findStatus() {
        List<Status> statuses=new ArrayList<>();
        for(StatusEnum statusEnum : StatusEnum.values()) {
            statuses.add(new Status(statusEnum.getStatus(),statusEnum.getDescription()));
        }

        return statuses;
    }
}
