package com.JournalApp.journalapplication.controller;

import com.JournalApp.journalapplication.entity.JournalEntry;
import com.JournalApp.journalapplication.entity.User;
import com.JournalApp.journalapplication.service.JournalEntryService;
import com.JournalApp.journalapplication.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journals")
public class JournalEntryControlV2 {
    @Autowired
    private JournalEntryService journalentryservice;
//    @GetMapping
//    public List<JournalEntry> getall(){
//        return journalentryservice.getalljournalentry();
//    }
    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<?>getJournalEntryOfUser(@PathVariable String username) {
        User user = userService.getUserByName(username);
        List<JournalEntry>entriesOfUser = user.getJournalEntries();
        if(entriesOfUser!=null&& !entriesOfUser.isEmpty()){
            return new ResponseEntity<>(entriesOfUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{username}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry, @PathVariable String username) {
        try {
            User user = userService.getUserByName(username);
            myEntry.setDate(LocalDateTime.now());
            journalentryservice.savejournalEntry(myEntry);
            user.getJournalEntries().add(myEntry);
            userService.saveUserEntry(user);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> getJournalByEntry(@PathVariable ObjectId myId){
        Optional<JournalEntry>journalentry  = Optional.ofNullable(journalentryservice.getjournalentry(myId));
        if(journalentry.isPresent()){
            return new ResponseEntity<>(journalentry.get(),  HttpStatus.OK) ;
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/id/{username}/{myId}")
    public ResponseEntity<?> deleteEntry(@PathVariable ObjectId myId, @PathVariable String username) {
        User user = userService.getUserByName(username);
        user.getJournalEntries().remove(journalentryservice.getjournalentry(myId));
        userService.saveUserEntry(user);
        journalentryservice.deletejournalentry(myId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
    @PutMapping("/id/{myid}")
    public void updateEntry(@PathVariable ObjectId myid, @RequestBody JournalEntry myEntry){
        journalentryservice.updatejournalentryById(myid,myEntry);
    }

}
