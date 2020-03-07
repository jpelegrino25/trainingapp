package com.julioluis.trainingrest.services.impl;

import com.julioluis.trainingrest.entities.SessionRegister;
import com.julioluis.trainingrest.entities.Status;
import com.julioluis.trainingrest.repositories.SessionRegisterRepository;
import com.julioluis.trainingrest.services.SessionRegisterService;
import com.julioluis.trainingrest.utils.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionRegisterServiceImpl implements SessionRegisterService {

    @Autowired
    private SessionRegisterRepository sessionRegisterRepository;

    @Override
    public void save(SessionRegister sessionRegister) {
        sessionRegister.setStatus(new Status(StatusEnum.REGISTERED.getStatus()));
        sessionRegisterRepository.save(sessionRegister);

    }
}
