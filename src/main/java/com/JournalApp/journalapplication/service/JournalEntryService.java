package com.JournalApp.journalapplication.service;

import com.JournalApp.journalapplication.entity.JournalEntry;
import com.JournalApp.journalapplication.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public void savejournalEntry(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
    }
    public List<JournalEntry> getalljournalentry() {
        return journalEntryRepository.findAll();
    }
    public JournalEntry getjournalentry(ObjectId myid) {
        return journalEntryRepository.findById(myid).get();
    }
    public void deletejournalentry(ObjectId myid) {
        journalEntryRepository.deleteById(myid);
    }
    public boolean updatejournalentryById(ObjectId myid, JournalEntry newEntry) {
        JournalEntry old =  journalEntryRepository.findById(myid).get();
        if(old!=null) {
            old.setTitle(newEntry.getTitle()!=null&& !newEntry.getTitle().equals("")?newEntry.getTitle():old.getTitle());
            old.setContent(newEntry.getContent()!=null && !newEntry.getContent().equals("")?newEntry.getContent():old.getContent());
        }
        journalEntryRepository.save(old);
        return true;
    }
}
