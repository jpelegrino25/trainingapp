package com.julioluis.trainingrest.utils;

import com.julioluis.trainingrest.entities.Attendance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class AttendanceHelper {


    public void validateStudent(List<Attendance> attendances) throws BusinessException{
        if(attendances.isEmpty())
            throw new BusinessException("Attendance at least should have one student");
        for(Attendance attendance : attendances) {
            if(Objects.isNull(attendance.getStudent()))
                throw new BusinessException("Student should not be null in attendance");
        }

    }
}
