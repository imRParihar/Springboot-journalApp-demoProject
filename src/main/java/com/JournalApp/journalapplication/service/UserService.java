package com.JournalApp.journalapplication.service;

import com.JournalApp.journalapplication.entity.JournalEntry;
import com.JournalApp.journalapplication.entity.User;
import com.JournalApp.journalapplication.repository.JournalEntryRepository;
import com.JournalApp.journalapplication.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void saveUserEntry(User user) {
        userRepository.save(user);
    }
    public List<User> getallUsers() {
        return userRepository.findAll();
    }
    public User getUser(ObjectId myid) {
        return userRepository.findById(myid).get();
    }
    public User getUserByName(String username) {
        return userRepository.findByUsername(username);
    }
    public void deleteUserentry(ObjectId myid) {
        userRepository.deleteById(myid);
    }
//    public boolean updatejournalentryById(ObjectId myid, JournalEntry newEntry) {
//        User old =  userRepository.findById(myid).get();
//        if(old!=null) {
//
//        }
//        userRepository.save(old);
//        return true;
//    }
}
