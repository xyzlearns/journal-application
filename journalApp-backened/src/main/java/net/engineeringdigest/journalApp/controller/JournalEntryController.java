package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.JournalEntryServices;
import net.engineeringdigest.journalApp.service.UserServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
class JournalEntryController {

    @Autowired
    private JournalEntryServices journalEntryServices;

    @Autowired
    private UserServices userServices;

    @GetMapping("{userName}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName){
        User user = userServices.findByUserName(userName);
        List<JournalEntry> all = user.getJournalEntries();

        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("{userName}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry entry,@PathVariable String userName){
        try{
            journalEntryServices.saveEntry(userName,entry);
            return new ResponseEntity<>(entry,HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("id/{entryId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId entryId){
        Optional<JournalEntry> entry = journalEntryServices.findById(entryId);
        if(entry.isPresent()){
            return new ResponseEntity<>(entry.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{userName}/{entryId}")
    public ResponseEntity<?> delete(@PathVariable ObjectId entryId,@PathVariable String userName){
        journalEntryServices.deleteById(entryId,userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("id/{entryId}")
    public ResponseEntity<?> update(@PathVariable ObjectId entryId,@RequestBody JournalEntry entry){
        return journalEntryServices.updateById(entryId,entry);

    }
}
