package com.julioluis.trainingrest.services;

import com.julioluis.trainingrest.entities.Attendance;

import java.util.List;

public interface AttendanceService {

    List<Attendance> save(List<Attendance> attendances);
}
