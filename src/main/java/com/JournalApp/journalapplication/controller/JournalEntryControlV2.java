package com.JournalApp.journalapplication.controller;

import com.JournalApp.journalapplication.entity.JournalEntry;
import com.JournalApp.journalapplication.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journals")
public class JournalEntryControlV2 {
    @Autowired
    private JournalEntryService journalentryservice;

    @GetMapping
    public List<JournalEntry> getall(){
        return journalentryservice.getalljournalentry();
    }
    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry){
        myEntry.setDate(LocalDateTime.now());
        journalentryservice.savejournalEntry(myEntry);
        return true;
    }
    @GetMapping("/id/{myId}")
    public JournalEntry getJournalByEntry(@PathVariable ObjectId myId){
        return journalentryservice.getjournalentry(myId);
    }
    @DeleteMapping("/id/{myId}")
    public boolean deleteEntry(@PathVariable ObjectId myId){
        journalentryservice.deletejournalentry(myId);
        return true;
    }
    @PutMapping("/id/{myid}")
    public void updateEntry(@PathVariable ObjectId myid, @RequestBody JournalEntry myEntry){
        journalentryservice.updatejournalentryById(myid,myEntry);
    }

}
