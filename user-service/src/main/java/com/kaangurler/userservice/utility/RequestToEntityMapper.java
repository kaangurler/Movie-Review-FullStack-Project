package com.kaangurler.userservice.utility;

import com.kaangurler.userservice.entity.User;
import com.kaangurler.userservice.request.UserRequest;

public class RequestToEntityMapper {
    public static User userRequestToUser(UserRequest userRequest) {
        return User.builder()
                .userName(userRequest.userName)
                .password(userRequest.password)
                .build();

    }
}
