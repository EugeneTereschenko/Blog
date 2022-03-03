package com.ittest.blog.controllers;

import com.ittest.blog.models.User;
import com.ittest.blog.service.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserDetailsServiceImpl userDetailsService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userDetailsService.getUsers();
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @GetMapping(path = "/{userId}")
    public ResponseEntity<User> getUser(@PathVariable("userId") Long userId) {
        User user = userDetailsService.getUser(userId);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> registerNewUser(@RequestBody User user) {
        userDetailsService.saveUser(user);
        return new ResponseEntity<>("Add User", HttpStatus.OK);
    }

    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") Long userId) {
        userDetailsService.deleteUser(userId);
        return new ResponseEntity<>("Delete user", HttpStatus.OK);
    }

    @PutMapping(path="/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable("userId") Long userId, @RequestBody User user) {
        User userTemp = userDetailsService.updateUser(userId, user);
        return new ResponseEntity<User>(userTemp, HttpStatus.OK);
    }
}
