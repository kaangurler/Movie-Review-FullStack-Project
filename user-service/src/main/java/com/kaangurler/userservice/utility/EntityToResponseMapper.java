package com.kaangurler.userservice.utility;

import com.kaangurler.userservice.entity.User;
import com.kaangurler.userservice.response.UserResponse;

public class EntityToResponseMapper {
    public static UserResponse userToUserResponse(User user) {
        return UserResponse.builder()
                .userName(user.userName)
                .build();
    }
}
