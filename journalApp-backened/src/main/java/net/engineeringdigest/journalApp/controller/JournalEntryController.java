package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.JournalEntryServices;
import net.engineeringdigest.journalApp.service.UserServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @GetMapping()
    public ResponseEntity<?> getAllJournalEntriesOfUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userServices.findByUserName(userName);
        List<JournalEntry> all = user.getJournalEntries();

        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry entry){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        try{
            journalEntryServices.saveEntry(userName,entry);
            return new ResponseEntity<>(entry,HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("id/{entryId}")
    public ResponseEntity<?> delete(@PathVariable ObjectId entryId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        journalEntryServices.deleteById(entryId,userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("id/{entryId}")
    public ResponseEntity<?> update(@PathVariable ObjectId entryId,@RequestBody JournalEntry entry){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        return journalEntryServices.updateById(entryId,entry,userName);

    }
}
