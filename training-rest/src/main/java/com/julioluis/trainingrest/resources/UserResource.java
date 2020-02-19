package com.julioluis.trainingrest.resources;

import com.julioluis.trainingrest.entities.User;
import com.julioluis.trainingrest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<User> currentUser(Principal principal) {
      User user=userService.findUserByUsername(principal.getName());
      user.setPassword(null);

        return ResponseEntity.ok()
                .body(user);
    }

    @GetMapping(path = "auth")
    @ResponseBody
    public ResponseEntity<User> authenticatedUser(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user=userService.findUserByUsername(userDetails.getUsername());
        user.setPassword(null);

        return ResponseEntity.ok()
                .body(user);
    }


}
