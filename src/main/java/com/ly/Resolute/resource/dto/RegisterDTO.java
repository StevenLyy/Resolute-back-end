package com.ly.Resolute.resource.dto;

import com.ly.Resolute.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDTO {
    public String username;
    public String password;
    public String fullName;

    public RegisterDTO() {

    }

    public RegisterDTO(String username, String password, String fullName) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
    }
}
