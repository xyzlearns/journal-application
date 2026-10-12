package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class userController {

    @Autowired
    private UserServices services;

    @GetMapping
    public List<User> getAllUser(){
        return services.getAllUsers();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> findUserById(@PathVariable ObjectId id){
        return services.findUserById(id);
    }

    @PostMapping
    public void saveUser(@RequestBody User user){
        services.saveUser(user);
    }

    @PutMapping("edit/{userName}")
    public ResponseEntity<?> editUser(@PathVariable String userName, @RequestBody User user){
        return services.updateUser(userName,user);
    }

    @DeleteMapping("/id/{id}")
    public void deleteUserById(@PathVariable ObjectId id){
        services.deleteUserById(id);
    }
}
