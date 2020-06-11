package com.julioluis.trainingrest.services.impl;

import com.julioluis.trainingrest.entities.Attendance;
import com.julioluis.trainingrest.repositories.AttendanceRepository;
import com.julioluis.trainingrest.services.AttendanceService;
import com.julioluis.trainingrest.utils.AttendanceHelper;
import com.julioluis.trainingrest.utils.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private AttendanceHelper attendanceHelper;

    @Override
    public List<Attendance> save(List<Attendance> attendances) {
        try {
            attendanceHelper.validateStudent(attendances);
            List<Attendance> attendanceList = attendanceRepository.saveAll(attendances);
            return attendanceList;
        } catch (BusinessException e) {
            return null;
        }
    }
}
