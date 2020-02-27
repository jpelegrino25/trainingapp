package com.julioluis.trainingrest.resources;

import com.julioluis.trainingrest.entities.Session;
import com.julioluis.trainingrest.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sessions")
@CrossOrigin(origins = "http://localhost:3000")
public class SessionResource {

    @Autowired
    private SessionService sessionService;

    @GetMapping
    public ResponseEntity<List<Session>> findAll() {
        List<Session> sessionList=sessionService.findAll();

        return ResponseEntity.ok()
                .body(sessionList);
    }

    @GetMapping(path = "{sessionId}")
    public ResponseEntity<Session> getOne(@PathVariable(name = "sessionId") Integer id) {
        Session session=sessionService.findById(id);
        return ResponseEntity.ok()
                .body(session);
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody Session session) {
        sessionService.saveSession(session);
        return ResponseEntity.ok()
                .build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody Session session) {
        sessionService.updateSession(session);
        return ResponseEntity.ok()
                .build();
    }

    @DeleteMapping(path = "{sessionId}")
    public ResponseEntity<Void> delete(@PathVariable(name = "sessionId") Integer id) {
        sessionService.deleteSession(id);
        return ResponseEntity.ok()
                .build();
    }


}
