package com.kaangurler.userservice.request;

import lombok.Builder;

@Builder
public class UserRequest {
    public String userName;
    public String password;
}
