package com.kaangurler.userservice.service.impl;

import com.kaangurler.userservice.repository.UserRepository;
import com.kaangurler.userservice.request.UserRequest;
import com.kaangurler.userservice.response.UserResponse;
import com.kaangurler.userservice.service.UserService;
import com.kaangurler.userservice.utility.EntityToResponseMapper;
import com.kaangurler.userservice.utility.RequestToEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserResponse getById(Long id) {
        return EntityToResponseMapper.userToUserResponse(userRepository.findById(id).get());
    }

    @Override
    public List<UserResponse> getAll() {
        return userRepository.findAll().stream()
                .map(EntityToResponseMapper::userToUserResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void create(UserRequest userRequest) {
        userRepository.save(RequestToEntityMapper.userRequestToUser(userRequest));
    }

    @Override
    public String getUserNameById(Long id) {
        return userRepository.findById(id).get().getUserName();
    }
}
