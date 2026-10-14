package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/user")
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


    @PutMapping
    public ResponseEntity<?> editUser( @RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        return services.updateUser(userName,user);
    }

    @DeleteMapping
    public void deleteUserById(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = services.findByUserName(userName);

        services.deleteUserById(user.getId());
    }
}
