package com.ly.Resolute.service;

import com.ly.Resolute.exception.RoutineNotFoundException;
import com.ly.Resolute.exception.UserNotFoundException;
import com.ly.Resolute.model.Routine;
import com.ly.Resolute.model.User;
import com.ly.Resolute.repository.AuthorityRepo;
import com.ly.Resolute.repository.RoutineRepo;
import com.ly.Resolute.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepo userRepo;
    private final RoutineRepo routineRepo;
    private final AuthorityRepo authorityRepo;

    @Autowired
    public UserService(UserRepo userRepo, RoutineRepo routineRepo, AuthorityRepo authorityRepo) {
        this.userRepo = userRepo;
        this.routineRepo = routineRepo;
        this.authorityRepo = authorityRepo;
    }

    public User findUserById(long id){
        User user = userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User by id" + id + " not found"));
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(authorityRepo.findByUserId(id));
        user.setAuthorities(authorities);
        return user;
    }

    public List<User> findAllUsers(){
        List<User> users = userRepo.findAll();
        for(User user : users){
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(authorityRepo.findByUserId(user.getId()));
            user.setAuthorities(authorities);
        }
        return users;
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

    public Routine addRoutineToUser(Long userId, Long routineId){
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User by id" + userId + " not found"));
        Routine routine = routineRepo.findById(routineId)
                .orElseThrow(() -> new RoutineNotFoundException("Routine by id" + routineId + " not found"));
        user.addRoutineToUser(routine);
        userRepo.save(user);
        return routine;
    }
}