package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("/journal")
class JournalEntryController {

    private Map<Long,JournalEntry> journalEntries = new HashMap<>();

    @GetMapping
    public List<JournalEntry> getAll(){
        return new ArrayList<>(journalEntries.values());
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry entry){
        journalEntries.put(entry.getId(),entry);
        return true;
    }

    @GetMapping("id/{entryId}")
    public JournalEntry getJournalEntryById(@PathVariable long entryId){
        return journalEntries.get(entryId);
    }

    @DeleteMapping("id/{entryId}")
    public boolean delete(@PathVariable long entryId){
        journalEntries.remove(entryId);
        return true;
    }

    @PutMapping("id/{entryId}")
    public boolean update(@PathVariable long entryId,@RequestBody JournalEntry entry){
        journalEntries.put(entryId,entry);
        return true;
    }
}
