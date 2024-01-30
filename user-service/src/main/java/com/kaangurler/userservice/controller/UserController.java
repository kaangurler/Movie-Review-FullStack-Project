package com.kaangurler.userservice.controller;

import com.kaangurler.userservice.request.UserRequest;
import com.kaangurler.userservice.response.UserResponse;
import com.kaangurler.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class UserController {
    private final UserService userService;

    @PostMapping
    public void create(@RequestBody UserRequest userRequest) {
        userService.create(userRequest);
    }

    @GetMapping("/{id}")
    public UserResponse getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @GetMapping("/{id}/get-name")
    public String getUserNameById(@PathVariable Long id) {
        return userService.getUserNameById(id);
    }

    @GetMapping
    public List<UserResponse> getAll() {
        return userService.getAll();
    }
}
