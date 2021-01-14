package com.osworksapi.api.controller;

import com.osworksapi.domain.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("users/")
public class UserController {

    @GetMapping
    public List<User> list() {
        return Arrays.asList(
            new User(1L, "Jean", "jean@gmail.com", "81281458"),
            new User(2L, "Geovani", "geovani@gmail.com", "82184185")
        );
    }
}
