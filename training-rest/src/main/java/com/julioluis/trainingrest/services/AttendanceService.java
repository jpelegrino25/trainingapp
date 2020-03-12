package com.julioluis.trainingrest.services;

import com.julioluis.trainingrest.entities.Attendance;

import java.util.List;

public interface AttendanceService {

    void save(List<Attendance> attendances);
}
