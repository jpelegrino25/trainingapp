package com.julioluis.trainingrest.resources;

import com.julioluis.trainingrest.entities.Training;
import com.julioluis.trainingrest.services.TrainingService;
import com.julioluis.trainingrest.utils.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("trainings")
@CrossOrigin(origins = "http://localhost:3000")
public class TrainingResource {

    @Autowired
    private TrainingService trainingService;

    @GetMapping
    public ResponseEntity findAll() {
        List<Training> trainingList=trainingService.findAll();
        return ResponseEntity.ok().body(trainingList);
    }

    @GetMapping(path = "{trainingId}")
    public ResponseEntity<Training> getOne(@PathVariable(name = "trainingId") Integer id) {
        Training training=trainingService.findById(id);
        if (Objects.isNull(training))
            throw new UserException("Training not found");

        return ResponseEntity.ok()
                .body(training);
    }

    @PostMapping
    public ResponseEntity<Void> saveTraining(@RequestBody Training training) {
        Training trainingSaved = trainingService.save(training);

        URI uri= ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(trainingSaved.getId()).toUri();

        return ResponseEntity.created(uri)
                .build();
    }

    @PutMapping
    public ResponseEntity<Void> updateTraining(@RequestBody Training training) {
        trainingService.update(training);
        return ResponseEntity.ok()
                .build();
    }

    @DeleteMapping(path = "{trainingId}")
    public ResponseEntity<Void> deleteTraining(@PathVariable(name = "trainingId") Integer id) {
            trainingService.delete(id);
        return ResponseEntity.ok()
                .build();
    }
}
