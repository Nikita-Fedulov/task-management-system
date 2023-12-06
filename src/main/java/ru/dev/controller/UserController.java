package ru.dev.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dev.model.User;
import ru.dev.service.UserService;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable long userId) {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<Long> createUser(@RequestBody @Valid User user) {
        long userId = userService.createUser(user.getUsername(), user.getEmail(), user.getPassword());
        return ResponseEntity.ok(userId);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable long userId, @RequestBody @Valid User user) {
        userService.updateUser(userId, user.getUsername(), user.getEmail(), user.getPassword());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }
}

