package com.julioluis.trainingrest.services.impl;

import com.julioluis.trainingrest.entities.SessionRegister;
import com.julioluis.trainingrest.entities.Status;
import com.julioluis.trainingrest.repositories.SessionRegisterRepository;
import com.julioluis.trainingrest.services.SessionRegisterService;
import com.julioluis.trainingrest.utils.BusinessException;
import com.julioluis.trainingrest.utils.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SessionRegisterServiceImpl implements SessionRegisterService {

    @Autowired
    private SessionRegisterRepository sessionRegisterRepository;

    @Override
    public SessionRegister save(SessionRegister sessionRegister) throws BusinessException {
        if(Objects.isNull(sessionRegister.getRegisterSessionId()))
            throw new BusinessException("You must provide user and session id");
        if(Objects.isNull(sessionRegister.getRegisterSessionId().getSession()))
            throw new BusinessException("You must provide the session id");
        if(Objects.isNull(sessionRegister.getRegisterSessionId().getUser()))
            throw new BusinessException("You must provide the user id");

        sessionRegister.setStatus(new Status(StatusEnum.REGISTERED.getStatus()));
        SessionRegister register = sessionRegisterRepository.save(sessionRegister);

        return register;

    }

    @Override
    public List<SessionRegister> findAttendanceList(Integer proffesor, Integer sessionId) {
        List<SessionRegister> attendance=sessionRegisterRepository
                .findStudentsByProfessorAndSession(proffesor,sessionId);
        return attendance;
    }
}
