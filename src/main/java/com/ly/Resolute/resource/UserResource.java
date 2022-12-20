package com.ly.Resolute.resource;

import com.ly.Resolute.model.Routine;
import com.ly.Resolute.model.User;
import com.ly.Resolute.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5000"})
@RequestMapping("api/v1/users/")
public class UserResource {
    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.ACCEPTED);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id){
        User user = userService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user){
        User newUser = userService.addUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long id){
        User updateEmployee = userService.updateUser(user, id);
        return new ResponseEntity<>(updateEmployee, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUserById (@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping("increase/{id}")
    public ResponseEntity<String> increaseStreakOfUser(@PathVariable("id") long id){
        long updatedStreak = userService.increaseStreak(id);
        return new ResponseEntity<>("The current streak for this user is now: " + updatedStreak,HttpStatus.ACCEPTED);
    }

    @PostMapping("{userId}/routines/{routineId}")
    public ResponseEntity<String> addRoutineToUser(@PathVariable("userId") long userId, @PathVariable("routineId") long routineId){
        Routine routine = userService.addRoutineToUser(userId, routineId);
        return new ResponseEntity<>("Routine '" + routine.getName() + "' added to user", HttpStatus.ACCEPTED);
    }
}
