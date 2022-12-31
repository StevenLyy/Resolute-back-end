package com.ly.Resolute.service;

import com.ly.Resolute.model.User;
import com.ly.Resolute.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    private final UserRepo userRepo;

    @Autowired
    public RegisterService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void Register(String username, String password, String fullName){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(password);
        User user = new User(username, encodedPassword, fullName);
        userRepo.save(user);
    }
}
