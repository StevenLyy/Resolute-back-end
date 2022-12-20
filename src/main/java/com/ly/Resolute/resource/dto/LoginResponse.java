package com.ly.Resolute.resource.dto;

import com.ly.Resolute.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private User user;
    private String token;

    public LoginResponse(User user, String token) {
        this.user = user;
        this.token = token;
    }
    public LoginResponse(){}
}
