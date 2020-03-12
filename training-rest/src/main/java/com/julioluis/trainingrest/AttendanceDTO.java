package com.julioluis.trainingrest;

import com.julioluis.trainingrest.entities.Attendance;

import java.util.List;

public class AttendanceDTO {

    private List<Attendance> attendances;

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
    }
}
