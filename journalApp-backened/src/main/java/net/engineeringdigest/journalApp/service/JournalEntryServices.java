package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryServices {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserServices userServices;

    @Transactional
    public void saveEntry(String userName,JournalEntry entry){
        try{
            User user = userServices.findByUserName(userName);
            entry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(entry);
            user.getJournalEntries().add(saved);
            userServices.saveUser(user);
        }
        catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("an error occurred while saving the entry",e);
        }


    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public void deleteById(ObjectId id, String userName){
        try{
            User user = userServices.findByUserName(userName);
            user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            userServices.saveUser(user);
            journalEntryRepository.deleteById(id);
        }
        catch (Exception e){
            throw new RuntimeException("error occurred while  deleting entry",e);
        }

    }

    public ResponseEntity<?> updateById(ObjectId id, JournalEntry newEntry, String userName){
        JournalEntry entry = journalEntryRepository.findById(id).orElse(null);
        User user = userServices.findByUserName(userName);
        List<JournalEntry> journalEntries = user.getJournalEntries();
        boolean valid = false;
        for(JournalEntry j: journalEntries){
            if(j.getId().equals(id)) valid = true;
        }
        if(valid && entry!=null){
            entry.setTitle(newEntry.getTitle() != null ? newEntry.getTitle() : entry.getTitle());
            entry.setContent(newEntry.getContent() != null ? newEntry.getContent() : entry.getContent());
            journalEntryRepository.save(entry);
            return new ResponseEntity<JournalEntry>(entry, HttpStatus.OK);
        }

        return  new ResponseEntity<JournalEntry>(HttpStatus.NOT_FOUND);

    }

}
