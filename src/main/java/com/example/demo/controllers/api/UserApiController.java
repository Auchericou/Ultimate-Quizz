package com.example.demo.controllers.api;

import com.example.demo.entities.Personne;
import com.example.demo.entities.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class UserApiController {

    @Autowired
    private UserService userService;
    @GetMapping("user/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") int id)
    {
        Optional<User> user = this.userService.getUser(id);

        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("users")
    public List<User> getUser(@RequestParam(value = "username", required = false) String username) {
        if(username == null) {
            return this.userService.getUsers();
        }
        return this.userService.getUserByUsername(username);
    }

    @PostMapping("user")
    public User addUser(@RequestBody User newUser) {
        this.userService.addUser(newUser);
        return newUser;
    }

    @PutMapping("user/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable("id") int id,
            @RequestBody User newUser
    ) {
        try {
            return ResponseEntity.ok().body(this.userService.updateUser(id, newUser));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("user/{id}")
    public void deleteUser(@PathVariable("id") int id) {
        this.userService.deleteUser(id);
    }

}
