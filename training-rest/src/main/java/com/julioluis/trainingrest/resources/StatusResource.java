package com.julioluis.trainingrest.resources;

import com.julioluis.trainingrest.entities.Status;
import com.julioluis.trainingrest.services.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("statuses")
@CrossOrigin(origins = "http://localhost:3000")
public class StatusResource {

    @Autowired
    private StatusService statusService;

    @GetMapping
    public ResponseEntity<List<Status>> getStatus() {
        List<Status> statusEnumList=statusService.findStatus();
        return  ResponseEntity.ok()
                .body(statusEnumList);
    }
}
