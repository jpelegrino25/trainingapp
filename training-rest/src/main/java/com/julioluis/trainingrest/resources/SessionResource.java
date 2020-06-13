package com.julioluis.trainingrest.resources;

import com.julioluis.trainingrest.entities.Session;
import com.julioluis.trainingrest.services.SessionService;
import com.julioluis.trainingrest.utils.BusinessException;
import com.julioluis.trainingrest.utils.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

        try {
            Session session = sessionService.findById(id);
            return ResponseEntity.ok()
                    .body(session);
        } catch (BusinessException e) {
            throw new UserException(e.getMessage());
        }


    }

    @GetMapping(path = "availables/{userId}")
    public ResponseEntity<List<Session>>
        findAllAvailableSessions(@PathVariable(name = "userId") Integer userId) {

        try {
            List<Session> sessionList = sessionService.findAvailableSessions(userId);
            return ResponseEntity.ok()
                    .body(sessionList);

        } catch (BusinessException e) {
            throw new UserException(e.getMessage());
        }

    }

    @GetMapping(path = "instructor/{userId}")
    public ResponseEntity<List<Session>>
    findInstructorSessions(@PathVariable(name = "userId") Integer userId) {
        List<Session> sessionList=sessionService.findSessionsByInstructor(userId);

        return ResponseEntity.ok()
                .body(sessionList);
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody Session session) {
        try {
            Session saveSession = sessionService.saveSession(session);

            URI uri= ServletUriComponentsBuilder.fromCurrentRequestUri()
                    .path("/{id}").buildAndExpand(saveSession.getId()).toUri();

            return ResponseEntity.created(uri)
                    .build();
        } catch (BusinessException e) {
            throw new UserException(e.getMessage());
        }

    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody Session session) {
        sessionService.updateSession(session);
        return ResponseEntity.ok()
                .build();
    }

    @DeleteMapping(path = "{sessionId}")
    public ResponseEntity<Void> delete(@PathVariable(name = "sessionId") Integer id) {
        try {
            sessionService.deleteSession(id);
            return ResponseEntity.ok()
                    .build();
        } catch (BusinessException e) {
            throw new UserException(e.getMessage());
        }

    }


}
