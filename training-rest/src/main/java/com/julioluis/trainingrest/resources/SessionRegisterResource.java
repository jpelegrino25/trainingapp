package com.julioluis.trainingrest.resources;

import com.julioluis.trainingrest.entities.SessionRegister;
import com.julioluis.trainingrest.services.SessionRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("registers")
@CrossOrigin(origins = "http://localhost:3000")
public class SessionRegisterResource {

    @Autowired
    private SessionRegisterService sessionRegisterService;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody SessionRegister sessionRegister) {
        sessionRegisterService.save(sessionRegister);

        return ResponseEntity.ok()
                .build();

    }

    @GetMapping(path = "attendance/{userId}/{sessionId}")
    public ResponseEntity<List<SessionRegister>> findAttendance(@PathVariable(name = "userId") Integer userId,
                                                                @PathVariable(name = "sessionId") Integer sessionId) {

        List<SessionRegister> sessionRegisterList=sessionRegisterService.findAttendanceList(userId,sessionId);

        return ResponseEntity.ok()
                .body(sessionRegisterList);
    }
}
