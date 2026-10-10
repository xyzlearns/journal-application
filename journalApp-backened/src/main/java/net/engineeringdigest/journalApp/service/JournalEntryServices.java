package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryServices {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public void saveEntry(JournalEntry entry){
        journalEntryRepository.save(entry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id){
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
