package com.ly.Resolute.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomPasswordEncoder {
    private PasswordEncoder passwordEncoder;
    public CustomPasswordEncoder(){
        passwordEncoder = new BCryptPasswordEncoder();
    }
    public PasswordEncoder getPasswordEncoder(){
        return passwordEncoder;
    }
}
