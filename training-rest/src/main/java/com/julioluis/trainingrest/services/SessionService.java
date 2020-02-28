package com.julioluis.trainingrest.services;

import com.julioluis.trainingrest.entities.Session;

import java.util.List;

public interface SessionService {

    List<Session> findAll();
    Session findById(Integer id);
    void saveSession(Session session);
    void updateSession(Session session);
    void deleteSession(Integer id);
    Session lastSession();
}
