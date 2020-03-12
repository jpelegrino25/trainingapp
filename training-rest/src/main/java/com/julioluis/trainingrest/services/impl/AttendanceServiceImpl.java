package com.julioluis.trainingrest.services.impl;

import com.julioluis.trainingrest.entities.Attendance;
import com.julioluis.trainingrest.repositories.AttendanceRepository;
import com.julioluis.trainingrest.services.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Override
    public void save(List<Attendance> attendances) {
        attendanceRepository.saveAll(attendances);
    }
}
