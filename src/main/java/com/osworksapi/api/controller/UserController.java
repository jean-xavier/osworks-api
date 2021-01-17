package com.osworksapi.api.controller;

import com.osworksapi.domain.model.User;
import com.osworksapi.domain.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("users/")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<User> list() {
        return userService.findAll();
    }

    @GetMapping("{id}")
    public User find(@PathVariable Long id) {
        return userService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User add(@Valid @RequestBody User user) {
        return  userService.save(user);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @Valid @RequestBody User user) {
        boolean exists = userService.existsById(id);

        if (!exists) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userService.update(id, user));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean exists = userService.existsById(id);

        if (!exists) {
            return ResponseEntity.notFound().build();
        }

        userService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
