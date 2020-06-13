package com.julioluis.trainingrest.resources;

import com.julioluis.trainingrest.entities.SessionRegister;
import com.julioluis.trainingrest.services.SessionRegisterService;
import com.julioluis.trainingrest.utils.BusinessException;
import com.julioluis.trainingrest.utils.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("registers")
@CrossOrigin(origins = "http://localhost:3000")
public class SessionRegisterResource {

    @Autowired
    private SessionRegisterService sessionRegisterService;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody SessionRegister sessionRegister) {
        try {
            SessionRegister register = sessionRegisterService.save(sessionRegister);

            Map<String,Integer> expandMap=new HashMap<>();
            expandMap.put("userId",register.getRegisterSessionId().getUser().getId());
            expandMap.put("sessionId",register.getRegisterSessionId().getSession().getId());

            URI uri= ServletUriComponentsBuilder.fromCurrentRequestUri()
                    .path("/{userId}/{sessionId}").buildAndExpand(expandMap)
                    .toUri();

            return ResponseEntity.created(uri)
                    .build();
        } catch (BusinessException e) {
            throw new UserException(e.getMessage());
        }



    }

    @GetMapping(path = "attendance/{userId}/{sessionId}")
    public ResponseEntity<List<SessionRegister>> findAttendance(@PathVariable(name = "userId") Integer userId,
                                                                @PathVariable(name = "sessionId") Integer sessionId) {

        List<SessionRegister> sessionRegisterList=sessionRegisterService.findAttendanceList(userId,sessionId);

        return ResponseEntity.ok()
                .body(sessionRegisterList);
    }
}
