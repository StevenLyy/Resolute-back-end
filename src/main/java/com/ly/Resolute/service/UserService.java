package com.ly.Resolute.service;

import com.ly.Resolute.exception.UserNotFoundException;
import com.ly.Resolute.model.User;
import com.ly.Resolute.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User findUserById(long id){
        return userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id" + id + " not found"));
    }

    public List<User> findAllUsers(){
        return userRepo.findAll();
    }

    public User addUser(User user){return userRepo.save(user);}

    public User updateUser(User user, Long id){
        User updatedUser = userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id" + id + " not found"));
        updatedUser.setFullName(user.getFullName());
        return userRepo.save(updatedUser);
    }

    public void deleteUser(Long id){
        userRepo.deleteById(id);
    }

    public long increaseStreak(long userId){
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User by id" + userId + " not found"));
        long updatedStreak = user.increaseStreak();
        userRepo.save(user);
        return updatedStreak;
    }
}