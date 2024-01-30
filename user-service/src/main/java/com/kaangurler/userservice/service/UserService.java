package com.kaangurler.userservice.service;

import com.kaangurler.userservice.request.UserRequest;
import com.kaangurler.userservice.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse getById(Long id);
    List<UserResponse> getAll();
    void create(UserRequest userRequest);
    String getUserNameById(Long id);
}
