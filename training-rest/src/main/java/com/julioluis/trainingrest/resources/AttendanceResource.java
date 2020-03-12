package com.julioluis.trainingrest.resources;

import com.julioluis.trainingrest.AttendanceDTO;
import com.julioluis.trainingrest.services.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("attendances")
@CrossOrigin(origins = "http://localhost:3000")
public class AttendanceResource {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody AttendanceDTO attendanceDTO) {
        attendanceService.save(attendanceDTO.getAttendances());
        return ResponseEntity.ok()
                .build();
    }
}
