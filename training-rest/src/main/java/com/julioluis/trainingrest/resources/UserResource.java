package com.julioluis.trainingrest.resources;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.julioluis.trainingrest.entities.Rol;
import com.julioluis.trainingrest.entities.User;
import com.julioluis.trainingrest.services.UserService;
import com.julioluis.trainingrest.utils.BusinessException;
import com.julioluis.trainingrest.utils.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserResource {


    @Autowired
    private UserService userService;

//    @GetMapping
//    public ResponseEntity<MappingJacksonValue> getAll() {
//        List<User> userList=userService.findAllUser();
//
//        MappingJacksonValue mapping=new MappingJacksonValue(userList);
//        SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter.filterOutAllExcept("firstname","lastname","emailAddress","rol");
//        FilterProvider filterProvider=new SimpleFilterProvider().addFilter("UserFilter",filter);
//
//        mapping.setFilters(filterProvider);
//
//
//        return ResponseEntity.ok()
//                .body(mapping);
//    }

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

        if(Objects.isNull(user))
            throw new UserException("User not found");

        return ResponseEntity.ok()
                .body(user);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid User user) {

        try {
          User userSaved=userService.saveUser(user);
            URI uri= ServletUriComponentsBuilder.fromCurrentRequestUri()
                    .path("/{id}").buildAndExpand(userSaved.getId()).toUri();

            return ResponseEntity.created(uri).build();

        } catch (BusinessException e) {
            throw new UserException(e.getMessage());
        }

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
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok().build();
        } catch (BusinessException e) {
            throw new UserException(e.getMessage());
        }

    }

    @GetMapping(path = "roles")
    public ResponseEntity<List<Rol>> getRoles() {
        List<Rol> rolList=userService.findAllRoles();

        return ResponseEntity.ok()
                .body(rolList);
    }


}
