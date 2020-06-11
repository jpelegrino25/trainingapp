package com.julioluis.trainingrest.utils.prototypes;

import com.julioluis.trainingrest.entities.*;
import com.julioluis.trainingrest.utils.StatusEnum;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PrototypeFactory {

    private static Map<ModelType,TrainingProptotype> proptotypeMap=new HashMap<>();

    static {
        // User Template
        User user=new User();
        user.setUsername("julio");
        user.setPassword("admin");
        Rol rol=new Rol();
        rol.setId(1);
        rol.setDescription("ADMIN");
        Authority authority = new Authority();
        authority.setDescription("TRAINING_MAINTANANCE");
        rol.setAuthorities(Arrays.asList(authority));
        user.setRol(rol);
        user.setStatus(new Status(StatusEnum.ACTIVE.getStatus()));

//        Training Template
        Training training=new Training();
        training.setStatus(new Status(StatusEnum.ACTIVE.getStatus()));
        training.setDescription("Training Test");


        Session session=new Session();
        session.setStartDate(new Date());
        session.setStatus(new Status(StatusEnum.ACTIVE.getStatus()));


        SessionRegister sessionRegister=new SessionRegister();
        RegisterSessionId registerSessionId=new RegisterSessionId();
        Session session1=new Session();
        session1.setId(13);
        registerSessionId.setSession(session1);
        User user1 = new User();
        user1.setId(3);
        registerSessionId.setUser(user1);
        sessionRegister.setRegisterSessionId(registerSessionId);



        proptotypeMap.put(ModelType.USER,user);
        proptotypeMap.put(ModelType.TRAINING,training);
        proptotypeMap.put(ModelType.SESSION,session);
        proptotypeMap.put(ModelType.SESSION_REGISTER,sessionRegister);

    }

    public static TrainingProptotype trainingProptotype(ModelType type) throws CloneNotSupportedException {
        return proptotypeMap.get(type).clone();
    }


}
