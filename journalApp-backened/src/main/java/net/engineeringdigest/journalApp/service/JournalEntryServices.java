package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryServices {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserServices userServices;

    public void saveEntry(String userName,JournalEntry entry){
        User user = userServices.findByUserName(userName);
        entry.setDate(LocalDateTime.now());
        JournalEntry saved = journalEntryRepository.save(entry);
        user.getJournalEntries().add(saved);
        userServices.saveUser(user);

    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id, String userName){
        User user = userServices.findByUserName(userName);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userServices.saveUser(user);
        journalEntryRepository.deleteById(id);
    }

    public ResponseEntity<?> updateById(ObjectId id, JournalEntry newEntry){
        JournalEntry entry = journalEntryRepository.findById(id).orElse(null);
        if(entry!=null){
            entry.setTitle(newEntry.getTitle() != null ? newEntry.getTitle() : entry.getTitle());
            entry.setContent(newEntry.getContent() != null ? newEntry.getContent() : entry.getContent());
            journalEntryRepository.save(entry);
            return new ResponseEntity<JournalEntry>(entry, HttpStatus.OK);
        }
        return  new ResponseEntity<JournalEntry>(HttpStatus.NOT_FOUND);

    }

}
