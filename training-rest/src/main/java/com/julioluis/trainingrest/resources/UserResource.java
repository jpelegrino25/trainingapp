package com.julioluis.trainingrest.resources;

import com.julioluis.trainingrest.entities.Rol;
import com.julioluis.trainingrest.entities.User;
import com.julioluis.trainingrest.services.UserService;
import com.julioluis.trainingrest.utils.BusinessException;
import com.julioluis.trainingrest.utils.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserResource {


    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        List<User> userList=userService.findAllUser();

        return ResponseEntity.ok()
                .body(userList);
    }

    @GetMapping(path = "trainers")
    public ResponseEntity<List<User>> getInstructors() {
        List<User> trainers=userService.findTrainers();

        return ResponseEntity.ok()
                .body(trainers);
    }

    @GetMapping(path = "{userId}")
    public ResponseEntity<User>
        getOne(@PathVariable(name = "userId") Integer id) {
        User user=userService.findById(id);

        return ResponseEntity.ok()
                .body(user);
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        User userSaved=null;
        try {
           userSaved=userService.saveUser(user);
        } catch (BusinessException e) {
            throw new UserException(e.getMessage());
        }
        return ResponseEntity.ok().body(userSaved);
    }


    @PutMapping
    public ResponseEntity<User> update(@RequestBody User user) {
        try {
           User userSaved= userService.saveUser(user);
            return ResponseEntity.ok().body(userSaved);
        } catch (BusinessException e) {
            throw new UserException(e.getMessage());
        }

    }

    @DeleteMapping(path = "{userId}")
    public ResponseEntity<Void> delete(@PathVariable(name = "userId") Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "roles")
    public ResponseEntity<List<Rol>> getRoles() {
        List<Rol> rolList=userService.findAllRoles();

        return ResponseEntity.ok()
                .body(rolList);
    }


}
