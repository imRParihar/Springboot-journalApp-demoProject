package com.JournalApp.journalapplication.controller;

import com.JournalApp.journalapplication.entity.User;
import com.JournalApp.journalapplication.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getallUsers();
    }
    @GetMapping("/name/{userid}")
    public User getUserById(@PathVariable ObjectId userid) {
        return userService.getUser(userid);
    }
    @PostMapping
    public void createUser(@RequestBody User user) {
        userService.saveUserEntry(user);
    }
    @PutMapping("/{username}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String username) {
        User userDB = userService.getUserByName(username);
        if (userDB != null) {
            userDB.setUsername(user.getUsername());
            userDB.setPassword(user.getPassword());
            userService.saveUserEntry(userDB);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
